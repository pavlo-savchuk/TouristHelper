/*
 * The MIT License (MIT)
 *  Copyright (c) 2014 Lemberg Solutions Limited
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *   The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *   IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *   FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *   AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *   LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *   OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *   SOFTWARE.
 */

package com.ls.http.base.client;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.ls.http.base.BaseRequest;
import com.ls.http.base.BaseRequest.OnResponseListener;
import com.ls.http.base.ResponseData;
import com.ls.http.base.login.AnonymousLoginManager;
import com.ls.http.base.login.ILoginManager;
import com.ls.util.internal.ContentResolverRequestQueue;
import com.ls.util.internal.VolleyResponseUtils;

import android.content.ContentResolver;
import android.content.Context;
import android.net.Uri;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

/**
 * Class is used to generate requests based on DrupalEntities and attach them to request queue
 *
 * @author lemberg
 */
public class LSClient implements OnResponseListener {

    public enum DuplicateRequestPolicy {ALLOW, ATTACH, REJECT}

    @NonNull
    private final ResponseListenersSet listeners = new ResponseListenersSet();

    private RequestQueue mDefaultQueue;
    private RequestQueue mContentResolverQueue;

    private String mDefaultCharset;

    private ILoginManager mLoginManager;
    private RequestProgressListener progressListener;

    private int mRequestTimeout = 1500;

    private DuplicateRequestPolicy mDuplicateRequestPolicy = DuplicateRequestPolicy.ATTACH;

    public interface OnResponseListener {

        void onResponseReceived(@NonNull BaseRequest request, @NonNull ResponseData data, @Nullable Object tag);

        void onError(@NonNull BaseRequest request, @Nullable ResponseData data, @Nullable Object tag);

        void onCancel(@NonNull BaseRequest request, @Nullable Object tag);
    }

    /**
     * Can be used in order to react on request count changes (start/success/failure or canceling).
     *
     * @author lemberg
     */
    public interface RequestProgressListener {

        /**
         * Called after new request was added to queue
         *
         * @param activeRequests number of requests pending
         */
        void onRequestStarted(LSClient theClient, int activeRequests);

        /**
         * Called after current request was complete
         *
         * @param activeRequests number of requests pending
         */
        void onRequestFinished(LSClient theClient, int activeRequests);
    }

    private LSClient() {

    }

    /**
     * @param theContext application context, used to create request queue
     * @deprecated use {@link Builder} instead
     */
    @Deprecated
    public LSClient(@NonNull Context theContext) {
        this(theContext, null);
    }

    /**
     * @param theContext      application context, used to create request queue
     * @param theLoginManager contains user profile data and can update request parameters and headers in order to apply it.
     * @deprecated use {@link Builder} instead
     */
    @Deprecated
    public LSClient(@NonNull Context theContext, @Nullable ILoginManager theLoginManager) {
        this(getDefaultQueue(theContext), theLoginManager);
    }

    @NonNull
    protected static RequestQueue getDefaultQueue(@NonNull Context theContext) {
        return Volley.newRequestQueue(theContext.getApplicationContext());
    }

    /**
     * @param theQueue        queue to execute requests. You can customize cache management, by setting custom queue
     * @param theLoginManager contains user profile data and can update request parameters and headers in order to apply it.
     * @deprecated use {@link #LSClient(Context, ILoginManager)} instead
     */
    @Deprecated
    public LSClient(@NonNull RequestQueue theQueue, @Nullable ILoginManager theLoginManager) {
        this.mDefaultQueue = theQueue;

        if (theLoginManager != null) {
            this.setLoginManager(theLoginManager);
        } else {
            this.setLoginManager(new AnonymousLoginManager());
        }
    }

    /**
     * @param request     Request object to be performed
     * @param synchronous if true request result will be returned synchronously
     * @return {@link ResponseData} object, containing request result code and string or error and deserialized object, specified in request.
     */
    public ResponseData performRequest(BaseRequest request, boolean synchronous) {
        return performRequest(request, null, null, synchronous);
    }

    /**
     * @param request     Request object to be performed
     * @param tag         will be applied to the request and returned in listener
     * @param synchronous if true request result will be returned synchronously
     * @return {@link ResponseData} object, containing request result code and string or error and deserialized object, specified in request.
     */
    public ResponseData performRequest(BaseRequest request, Object tag, final OnResponseListener listener, boolean synchronous) {
        request.setRetryPolicy(new DefaultRetryPolicy(mRequestTimeout, 1, 1));
        if (!mLoginManager.shouldRestoreLogin()) {
            return performRequestNoLoginRestore(request, tag, listener, synchronous);
        } else {
            return performRequestLoginRestore(request, tag, listener, synchronous);
        }
    }

    protected ResponseData performRequestNoLoginRestore(BaseRequest request, Object tag, OnResponseListener listener, boolean synchronous) {
        request.setTag(tag);
        request.setResponseListener(this);
        this.mLoginManager.applyLoginDataToRequest(request);
        request.setSmartComparisonEnabled(this.mDuplicateRequestPolicy != DuplicateRequestPolicy.ALLOW);

        boolean wasRegisterred;
        boolean skipDuplicateRequestListeners = this.mDuplicateRequestPolicy == LSClient.DuplicateRequestPolicy.REJECT;
        synchronized (listeners) {
            wasRegisterred = this.listeners.registerListenerForRequest(request, listener, tag, skipDuplicateRequestListeners);
        }

        if (wasRegisterred || synchronous) {
            this.onNewRequestStarted();
            return request.performRequest(synchronous, getRequestQueueForRequest(request));
        } else {
            if (skipDuplicateRequestListeners && listener != null) {
                listener.onCancel(request, tag);
            }
            return null;
        }
    }

    @NonNull
    private RequestQueue getRequestQueueForRequest(@NonNull final Request request) {
        if (mDefaultQueue == null) {
            throw new IllegalStateException("mDefaultQueue was not initialized");
        }
        final Uri uri = Uri.parse(request.getUrl());
        switch (uri.getScheme()) {
            case ContentResolver.SCHEME_ANDROID_RESOURCE:
            case ContentResolver.SCHEME_CONTENT:
            case ContentResolver.SCHEME_FILE:
            case ContentResolverRequestQueue.SCHEME_ASSETS:
                if (mContentResolverQueue == null) {
                    throw new IllegalStateException("mContentResolverQueue was not initialized. Make sure you don't use a deprecated constructor");
                }
                return mContentResolverQueue;

            default:
                return mDefaultQueue;
        }
    }

    private ResponseData performRequestLoginRestore(final BaseRequest request, Object tag, final OnResponseListener listener, final boolean synchronous) {
        if (synchronous) {
            return performRequestLoginRestoreSynchrounous(request, tag, listener);
        } else {
            return performRequestLoginRestoreAsynchrounous(request, tag, listener);
        }
    }

    private ResponseData performRequestLoginRestoreAsynchrounous(final BaseRequest request, Object tag, final OnResponseListener listener) {
        final OnResponseListener loginRestoreResponseListener = new OnResponseListener() {
            @Override
            public void onResponseReceived(@NonNull final BaseRequest request,
                    @NonNull final ResponseData data,
                    @Nullable final Object tag) {
                if (listener != null) {
                    listener.onResponseReceived(request, data, tag);
                }
            }

            @Override
            public void onError(@NonNull final BaseRequest request,
                    @Nullable final ResponseData data,
                    @Nullable final Object tag) {
                if (data != null && VolleyResponseUtils.isAuthError(data.getError())) {
                    if (mLoginManager.canRestoreLogin()) {
                        new RestoreLoginAttemptTask(request, listener, tag, data).execute();
                    } else {
                        mLoginManager.onLoginRestoreFailed();
                        if (listener != null) {
                            listener.onError(request, data, tag);
                        }
                    }
                } else {
                    if (listener != null) {
                        listener.onError(request, data, tag);
                    }
                }
            }

            @Override
            public void onCancel(@NonNull final BaseRequest request,
                    @Nullable final Object tag) {
                if (listener != null) {
                    listener.onCancel(request, tag);
                }
            }
        };

        return performRequestNoLoginRestore(request, tag, loginRestoreResponseListener, false);
    }

    private ResponseData performRequestLoginRestoreSynchrounous(final BaseRequest request, Object tag, final OnResponseListener listener) {
        final OnResponseListener loginRestoreResponseListener = new OnResponseListener() {
            @Override
            public void onResponseReceived(@NonNull final BaseRequest request,
                    @NonNull final ResponseData data,
                    @Nullable final Object tag) {
                if (listener != null) {
                    listener.onResponseReceived(request, data, tag);
                }
            }

            @Override
            public void onError(@NonNull final BaseRequest request,
                    @Nullable final ResponseData data,
                    @Nullable final Object tag) {
                if (data != null && VolleyResponseUtils.isAuthError(data.getError())) {
                    if (!mLoginManager.canRestoreLogin()) {
                        if (listener != null) {
                            listener.onError(request, data, tag);
                        }
                    }
                } else {
                    if (listener != null) {
                        listener.onError(request, data, tag);
                    }
                }
            }

            @Override
            public void onCancel(@NonNull final BaseRequest request,
                    @Nullable final Object tag) {
                if (listener != null) {
                    listener.onCancel(request, tag);
                }
            }
        };

        ResponseData result = performRequestNoLoginRestore(request, tag, loginRestoreResponseListener, true);
        if (VolleyResponseUtils.isAuthError(result.getError())) {
            if (mLoginManager.canRestoreLogin()) {
                boolean restored = mLoginManager.restoreLoginData(getRequestQueueForRequest(request));
                if (restored) {
                    result = performRequestNoLoginRestore(request, tag, new OnResponseAuthListenerDecorator(listener), true);
                } else {
                    listener.onError(request, result, tag);
                }
            } else {
                mLoginManager.onLoginRestoreFailed();
            }
        }
        return result;
    }


    /**
     * @return request timeout millis
     */
    public int getRequestTimeout() {
        return mRequestTimeout;
    }

    /**
     * @param requestTimeout request timeout millis
     */
    public void setRequestTimeout(int requestTimeout) {
        this.mRequestTimeout = requestTimeout;
    }

    /**
     * @return true all necessary user id data is fetched and login can be restored automatically
     */
    public boolean isLogged() {
        return this.mLoginManager.canRestoreLogin();
    }

    public ILoginManager getLoginManager() {
        return mLoginManager;
    }

    public void setLoginManager(ILoginManager loginManager) {
        this.mLoginManager = loginManager;
    }

    /**
     * Synchronous login restore attempt
     *
     * @return false if login restore failed.
     */
    public boolean restoreLogin() {
        if (this.mLoginManager.canRestoreLogin()) {
            return this.mLoginManager.restoreLoginData(mDefaultQueue);
        }
        return false;
    }

    @Override
    public void onResponseReceived(ResponseData data, BaseRequest request) {
        synchronized (listeners) {
            List<ResponseListenersSet.ListenerHolder> listenerList = this.listeners.getListenersForRequest(request);
            this.listeners.removeListenersForRequest(request);
            this.onRequestComplete();
            if (listenerList != null) {
                for (ResponseListenersSet.ListenerHolder holder : listenerList) {
                    holder.getListener().onResponseReceived(request, data, holder.getTag());
                }
            }
        }
    }

    @Override
    public void onError(ResponseData data, BaseRequest request) {
        synchronized (listeners) {
            List<ResponseListenersSet.ListenerHolder> listenerList = this.listeners.getListenersForRequest(request);
            this.listeners.removeListenersForRequest(request);
            this.onRequestComplete();
            if (listenerList != null) {
                for (ResponseListenersSet.ListenerHolder holder : listenerList) {
                    holder.getListener().onError(request, data, holder.getTag());
                }
            }
        }
    }

    /**
     * @return Charset, used to encode/decode server request post body and response.
     */
    public String getDefaultCharset() {
        return mDefaultCharset;
    }

    /**
     * @param defaultCharset Charset, used to encode/decode server request post body and response.
     */
    public void setDefaultCharset(String defaultCharset) {
        this.mDefaultCharset = defaultCharset;
    }

    /**
     * @param tag Cancel all requests, containing given tag. If no tag is specified - all requests are canceled.
     */
    public void cancelByTag(final @NonNull Object tag) {
        this.cancelAllRequestsForListener(null, tag);
    }

    /**
     * Cancel all requests
     */
    public void cancelAll() {
        this.cancelAllRequestsForListener(null, null);
    }

    /**
     * @return current duplicate request policy
     */
    public DuplicateRequestPolicy getDuplicateRequestPolicy() {
        return mDuplicateRequestPolicy;
    }

    /**
     * Sets duplicate request handling policy according to parameter provided. Only simultaneous requests are compared (executing at the same time).
     *
     * @param duplicateRequestPolicy in case if
     *                               "ALLOW" - all requests are performed
     *                               "ATTACH" - only first unique request from queue will be performed all other listeners will be attached to this request and triggered.
     *                               "REJECT" - only first unique request from queue will be performed and it's listener triggered. "onCancel()" listener method will be called for all requests
     *                               skipped.
     *                               Default value is "ALLOW"
     */
    public void setDuplicateRequestPolicy(DuplicateRequestPolicy duplicateRequestPolicy) {
        this.mDuplicateRequestPolicy = duplicateRequestPolicy;
    }

    /**
     * Cancel all requests for given listener with tag
     *
     * @param theListener listener to cancel requests for in case if null passed- all requests for given tag will be canceled
     * @param theTag      to cancel requests for, in case if null passed- all requests for given listener will be canceled
     */
    public void cancelAllRequestsForListener(final @Nullable OnResponseListener theListener, final @Nullable Object theTag) {
        cancelAllRequestsForListener(mDefaultQueue, theListener, theTag);
        if (mContentResolverQueue != null) {
            cancelAllRequestsForListener(mContentResolverQueue, theListener, theTag);
        }
    }

    /**
     * Cancel all requests for given listener with tag
     *
     * @param theListener listener to cancel requests for in case if null passed- all requests for given tag will be canceled
     * @param theTag      to cancel requests for, in case if null passed- all requests for given listener will be canceled
     */
    private void cancelAllRequestsForListener(@NonNull final RequestQueue requestQueue,
            final @Nullable OnResponseListener theListener,
            final @Nullable Object theTag) {
        requestQueue.cancelAll(new RequestQueue.RequestFilter() {
            @Override
            public boolean apply(Request<?> request) {
                if (theTag == null || theTag.equals(request.getTag())) {
                    synchronized (listeners) {
                        List<ResponseListenersSet.ListenerHolder> listenerList = listeners
                                .getListenersForRequest(request);

                        if (theListener == null || (listenerList != null
                                && holderListContainsListener(listenerList, theListener))) {
                            if (listenerList != null) {
                                listeners.removeListenersForRequest(request);
                                for (ResponseListenersSet.ListenerHolder holder : listenerList) {
                                    holder.getListener()
                                            .onCancel((BaseRequest) request, holder.getTag());
                                }
                                LSClient.this.onRequestComplete();
                            }
                            return true;
                        }
                    }
                }

                return false;
            }
        });
    }

    protected static boolean holderListContainsListener(List<ResponseListenersSet.ListenerHolder> listenerList, OnResponseListener theListener) {
        if (theListener == null) {
            return false;
        }

        boolean listContainsListener = false;
        for (ResponseListenersSet.ListenerHolder holder : listenerList) {
            if (theListener.equals(holder.getListener())) {
                listContainsListener = true;
            }
        }

        return listContainsListener;
    }

    // Manage request progress

    /**
     * @return number of requests pending
     */
    public int getActiveRequestsCount() {
        synchronized (listeners) {
            return this.listeners.registeredRequestCount();
        }
    }

    public RequestProgressListener getProgressListener() {
        return progressListener;
    }

    public void setProgressListener(RequestProgressListener progressListener) {
        this.progressListener = progressListener;
    }

    private void onNewRequestStarted() {
        if (this.progressListener != null) {

            int requestCount = this.getActiveRequestsCount();
            this.progressListener.onRequestStarted(this, requestCount);

        }
    }

    private void onRequestComplete() {
        if (this.progressListener != null) {
            int requestCount = this.getActiveRequestsCount();
            this.progressListener.onRequestFinished(this, requestCount);
        }
    }

    private class OnResponseAuthListenerDecorator implements OnResponseListener {

        private OnResponseListener listener;

        OnResponseAuthListenerDecorator(OnResponseListener listener) {
            this.listener = listener;
        }

        @Override
        public void onResponseReceived(@NonNull final BaseRequest request,
                @NonNull final ResponseData data,
                @Nullable final Object tag) {
            if (listener != null) {
                this.listener.onResponseReceived(request, data, tag);
            }
        }

        @Override
        public void onError(@NonNull final BaseRequest request,
                @Nullable final ResponseData data,
                @Nullable final Object tag) {
            if (data != null && VolleyResponseUtils.isAuthError(data.getError())) {
                mLoginManager.onLoginRestoreFailed();
            }
            if (listener != null) {
                this.listener.onError(request, data, tag);
            }
        }

        @Override
        public void onCancel(@NonNull final BaseRequest request,
                @Nullable final Object tag) {
            if (listener != null) {
                this.listener.onCancel(request, tag);
            }
        }
    }

    private class RestoreLoginAttemptTask {

        private final BaseRequest request;
        private final OnResponseListener listener;
        private final Object tag;
        private final ResponseData originData;

        RestoreLoginAttemptTask(BaseRequest request, OnResponseListener listener, Object tag, ResponseData originData) {
            this.request = request;
            this.listener = listener;
            this.tag = tag;
            this.originData = originData;
        }

        public void execute() {
            new Thread() {
                @Override
                public void run() {

                    boolean restored = mLoginManager.restoreLoginData(mDefaultQueue);
                    if (restored) {
                        performRequestNoLoginRestore(request, tag, new OnResponseAuthListenerDecorator(listener), false);
                    } else {
                        listener.onError(request, originData, tag);
                    }
                }
            }.start();
        }
    }

    public static final class Builder {

        @NonNull
        private final Context mContext;

        private RequestQueue mDefaultQueue;
        private RequestQueue mContentResolverQueue;
        private String mDefaultCharset;

        private ILoginManager mLoginManager;
        private int mRequestTimeout = 1500;

        private DuplicateRequestPolicy mDuplicateRequestPolicy = DuplicateRequestPolicy.ATTACH;

        public Builder(@NonNull final Context context) {
            mContext = context.getApplicationContext();
        }

        public Builder setRequestQueue(@NonNull final RequestQueue defaultQueue) {
            mDefaultQueue = defaultQueue;
            return this;
        }

        public Builder setContentResolverRequestQueue(@NonNull final ContentResolverRequestQueue contentResolverQueue) {
            mContentResolverQueue = contentResolverQueue;
            return this;
        }

        public Builder setDefaultCharset(@NonNull final String defaultCharset) {
            this.mDefaultCharset = defaultCharset;
            return this;
        }

        public Builder setLoginManager(@NonNull final ILoginManager loginManager) {
            this.mLoginManager = loginManager;
            return this;
        }

        public Builder setRequestTimeout(@IntRange(from = 0) final int requestTimeout) {
            this.mRequestTimeout = requestTimeout;
            return this;
        }

        public Builder setDuplicateRequestPolicy(@NonNull final DuplicateRequestPolicy duplicateRequestPolicy) {
            this.mDuplicateRequestPolicy = duplicateRequestPolicy;
            return this;
        }

        @NonNull
        public LSClient build() {
            final LSClient client = new LSClient();
            client.mDefaultQueue = mDefaultQueue != null ? mDefaultQueue : getDefaultQueue(mContext);
            client.mContentResolverQueue = mContentResolverQueue != null ? mContentResolverQueue : new ContentResolverRequestQueue(mContext);
            client.mDefaultCharset = mDefaultCharset;
            client.mLoginManager = mLoginManager != null ? mLoginManager : new AnonymousLoginManager();
            client.mRequestTimeout = mRequestTimeout;
            client.mDuplicateRequestPolicy = mDuplicateRequestPolicy;

            client.mContentResolverQueue.start();
            return client;
        }
    }
}