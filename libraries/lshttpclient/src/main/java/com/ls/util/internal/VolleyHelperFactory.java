package com.ls.util.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.http.AndroidHttpClient;
import android.os.Build;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Log;

import com.android.volley.toolbox.HttpClientStack;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.HurlStack;
import com.ls.httpclient.R;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.File;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/**
 * Created by Yaroslav Mytkalyk on 18/12/15.
 */
public final class VolleyHelperFactory {

    @NonNull
    public static IVolleyHelper newHelper() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            return new VolleyHelperGingerbread();
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.FROYO) {
            return new VolleyHelperFroyo();
        } else {
            return new VolleyHelperDonut();
        }
    }

    @SuppressWarnings("deprecation")
    public interface IVolleyHelper {
        File getBestCacheDir(@NonNull Context context);

        HttpStack createHttpStack(@NonNull Context context);

        HttpClient createHttpClient(@NonNull Context context);
    }

    private static class VolleyHelperDonut implements IVolleyHelper {

        protected static final String CACHE_DIR_NAME = "volley";

        @Override
        public File getBestCacheDir(@NonNull final Context context) {
            return new File(context.getCacheDir(), CACHE_DIR_NAME);
        }

        @Override
        public HttpStack createHttpStack(@NonNull final Context context) {
            return new HttpClientStack(createHttpClient(context));
        }

        @SuppressWarnings("deprecation")
        @Override
        public HttpClient createHttpClient(@NonNull final Context context) {
            return new DefaultHttpClient();
        }
    }

    @TargetApi(Build.VERSION_CODES.FROYO)
    private static class VolleyHelperFroyo extends VolleyHelperDonut {

        @Override
        public File getBestCacheDir(@NonNull final Context context) {
            if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
                return new File(context.getExternalCacheDir(), CACHE_DIR_NAME);
            } else {
                return new File(context.getCacheDir(), CACHE_DIR_NAME);
            }
        }

        @SuppressWarnings("deprecation")
        @Override
        public HttpClient createHttpClient(@NonNull final Context context) {
            String userAgent;
            try {
                final String packageName = context.getPackageName();
                final PackageInfo info = context.getPackageManager().getPackageInfo(packageName, 0);
                userAgent = packageName + '/' + info.versionCode;
            } catch (PackageManager.NameNotFoundException e) {
                userAgent = "volley/0";
            }
            return AndroidHttpClient.newInstance(userAgent);
        }
    }

    @TargetApi(Build.VERSION_CODES.GINGERBREAD)
    private static class VolleyHelperGingerbread extends VolleyHelperFroyo {

        @Override
        public HttpStack createHttpStack(@NonNull final Context context) {
            return new HurlStack(null, getSSLSocketFactoryCertificate(context));
        }

        private SSLSocketFactory getSSLSocketFactoryCertificate(Context context) {
            try {
                String keyStoreType = "BKS";
                CertificateFactory cf = CertificateFactory.getInstance("X.509", "BC");
                InputStream caInput = context.getResources().openRawResource(R.raw.cert);

                Certificate ca = cf.generateCertificate(caInput);
                caInput.close();

                if (keyStoreType == null || keyStoreType.length() == 0) {
                    keyStoreType = KeyStore.getDefaultType();
                }
                KeyStore keyStore = KeyStore.getInstance(keyStoreType);
                keyStore.load(null, null);
                keyStore.setCertificateEntry("ca", ca);

                String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
                TrustManagerFactory tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
                tmf.init(keyStore);

                TrustManager[] wrappedTrustManagers = getWrappedTrustManagers(tmf.getTrustManagers());

                SSLContext sslContext = SSLContext.getInstance("TLS");
                sslContext.init(null, wrappedTrustManagers, null);

                return sslContext.getSocketFactory();
            } catch (Exception e) {
                return null;
            }
        }

        private TrustManager[] getWrappedTrustManagers(TrustManager[] trustManagers) {
            final X509TrustManager originalTrustManager = (X509TrustManager) trustManagers[0];
            return new TrustManager[]{
                    new X509TrustManager() {
                        public X509Certificate[] getAcceptedIssuers() {
                            return originalTrustManager.getAcceptedIssuers();
                        }

                        public void checkClientTrusted(X509Certificate[] certs, String authType) {
                            try {
                                if (certs != null && certs.length > 0) {
                                    certs[0].checkValidity();
                                } else {
                                    originalTrustManager.checkClientTrusted(certs, authType);
                                }
                            } catch (CertificateException e) {
                                Log.w("checkClientTrusted", e.toString());
                            }
                        }

                        public void checkServerTrusted(X509Certificate[] certs, String authType) {
                            try {
                                if (certs != null && certs.length > 0) {
                                    certs[0].checkValidity();
                                } else {
                                    originalTrustManager.checkServerTrusted(certs, authType);
                                }
                            } catch (CertificateException e) {
                                Log.w("checkServerTrusted", e.toString());
                            }
                        }
                    }
            };
        }
    }
}
