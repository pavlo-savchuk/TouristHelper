package ua.com.lviv.fly.touristhelper.data.base;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractDAO<ClassToSave extends AbstractVO<ClassId>, ClassId> {

    protected abstract String getSearchCondition();

    /**
     * @param theId the id of object to search
     * @return list of arguments, generated by object id.
     */
    protected abstract String[] getSearchConditionArguments(ClassId theId);

    protected abstract String getTableName();

    protected abstract void putObjectDataForSaving(ContentValues theValues,
            ClassToSave theObj);

    protected abstract void putObjectDataForUpdate(ContentValues theValues,
            ClassToSave theObj);

    /**
     * This method should return list of columns, used to define unique object
     * in "contains" method
     */
    protected abstract String[] getKeyColumns();

    protected abstract ClassToSave getObjectFromCursor(Cursor theCursor);

    public boolean containsData(ClassToSave theObj) {
        DatabaseFacade facade = DatabaseFacade.instance();
        return facade.containsRecord(getTableName(), getSearchCondition(),
                getSearchConditionArguments(theObj.getId()), getKeyColumns());
    }

    public int deleteData(ClassId theObj) {
        DatabaseFacade facade = DatabaseFacade.instance();
        return deleteData(getSearchCondition(),
                getSearchConditionArguments(theObj), false);
    }

    public int deleteData(String searchCondition, String[] arguments, boolean transaction) {
        DatabaseFacade facade = DatabaseFacade.instance();
        if (transaction) {
            facade.beginTransactions();
        }
        int result = facade.delete(getTableName(), searchCondition,
                arguments);

        if (transaction) {
            if (result > 0) {
                facade.setTransactionSuccesfull();
            }
            facade.endTransactions();
        }
        return result;
    }

    public int deleteData(List<ClassId> theObjs) {
        DatabaseFacade facade = DatabaseFacade.instance();
        facade.beginTransactions();
        int removed = 0;
        for (ClassId objId : theObjs) {
            removed += deleteData(objId);
        }
        facade.setTransactionSuccesfull();
        facade.endTransactions();
        return removed;
    }

    public int deleteDataSafe(String searchCondition, String[] arguments, boolean transaction) {
        DatabaseFacade facade = DatabaseFacade.instance();

        try {
            facade.open();

            return this.deleteData(searchCondition, arguments, transaction);
        } finally {
            facade.close();
        }
    }

    public int deleteDataSafe(List<ClassId> theObjs) {
        DatabaseFacade facade = DatabaseFacade.instance();

        try {
            facade.open();

            return this.deleteData(theObjs);
        } finally {
            facade.close();
        }
    }

    public int deleteDataSafe(ClassId theObjId) {
        DatabaseFacade facade = DatabaseFacade.instance();

        try {
            facade.open();

            return this.deleteData(theObjId);
        } finally {
            facade.close();
        }
    }

    public int deleteAll() {
        DatabaseFacade facade = DatabaseFacade.instance();
        return facade.delete(getTableName(), null, null);
    }

    public int deleteAllSafe() {
        DatabaseFacade facade = DatabaseFacade.instance();

        try {
            facade.open();

            return deleteAll();
        } finally {
            facade.close();
        }
    }

    public List<ClassToSave> getDataSafe(final String theCondition, final String[] theArguments) {
        DatabaseFacade facade = DatabaseFacade.instance();

        try {
            facade.open();
            return getData(theCondition, theArguments);

        } finally {
            facade.close();
        }

    }

    public List<ClassToSave> getDataSafe(ClassId theObj) {
        return getDataSafe(getSearchCondition(), getSearchConditionArguments(theObj));
    }

    public List<ClassToSave> getData(final String theCondition, final String[] theArguments) {

        DatabaseFacade facade = DatabaseFacade.instance();
        Cursor cursor = null;
        cursor = facade.getAllRecords(getTableName(), null,
                theCondition, theArguments);
        List<ClassToSave> result = new ArrayList<ClassToSave>(cursor.getCount());
        boolean moved = cursor.moveToFirst();
        while (moved) {
            ClassToSave obj = readData(cursor);

            result.add(obj);

            moved = cursor.moveToNext();
        }

        if (cursor != null) {
            cursor.close();
        }

        return result;
    }

    public List<ClassToSave> getData(ClassId theObj) {
        return getData(getSearchCondition(), getSearchConditionArguments(theObj));
    }

    public List<ClassToSave> getAllSafe() {
        final DatabaseFacade facade = DatabaseFacade.instance();
        try {
            facade.open();
            return getAll();

        } finally {
            facade.close();
        }
    }

    public List<ClassToSave> getAll() {
        return getData(null, null);
    }

    public void saveDataSafe(final List<ClassToSave> theList) {
        DatabaseFacade facade = DatabaseFacade.instance();
        try {
            facade.open();
            saveData(theList);
        } finally {
            facade.close();
        }
    }

    public void saveDataSafe(final ClassToSave theItem) {
        DatabaseFacade facade = DatabaseFacade.instance();
        try {
            facade.open();
            saveData(theItem);
        } finally {
            facade.close();
        }
    }

    public void saveData(final List<ClassToSave> theList) {
//        DatabaseFacade facade = DatabaseFacade.instance();
//        facade.beginTransactions();
        for (ClassToSave obj : theList) {
            saveData(obj);
        }
//        facade.setTransactionSuccesfull();
//        facade.endTransactions();
    }

    public void saveOrUpdateData(final List<ClassToSave> theList) {
//        DatabaseFacade facade = DatabaseFacade.instance();
//        facade.beginTransactions();
        for (ClassToSave obj : theList) {
            saveOrUpdate(obj);
        }
//        facade.setTransactionSuccesfull();
//        facade.endTransactions();
    }

    public void saveOrUpdateDataSafe(final List<ClassToSave> theList) {
        DatabaseFacade facade = DatabaseFacade.instance();
        try {
            facade.open();
            saveOrUpdateData(theList);
        } finally {
            facade.close();
        }
    }

    public void saveOrUpdateSafe(final ClassToSave theObj) {
        DatabaseFacade facade = DatabaseFacade.instance();

        try {
            facade.open();
            saveOrUpdate(theObj);
        } finally {
            facade.close();
        }
    }

    public void saveOrUpdate(final ClassToSave theObj) {
        if (theObj == null) {
            throw new IllegalArgumentException("Object can't be null");
        }

        ContentValues values = new ContentValues();
        putObjectDataForSaving(values, theObj);

        DatabaseFacade facade = DatabaseFacade.instance();
        facade.save(getTableName(), values, SQLiteDatabase.CONFLICT_REPLACE);
    }

    public long saveData(final ClassToSave theObj) {

        if (theObj == null) {
            throw new IllegalArgumentException("Object can't be null");
        }
        ContentValues values = new ContentValues();

        putObjectDataForSaving(values, theObj);
        DatabaseFacade facade = DatabaseFacade.instance();
        return facade.save(getTableName(), values);
    }

    public int updateData(final ClassToSave theObj) {
        if (theObj == null) {
            throw new IllegalArgumentException("Object can't be null");
        }

        ContentValues values = new ContentValues();
        putObjectDataForUpdate(values, theObj);

        DatabaseFacade facade = DatabaseFacade.instance();
        return facade.update(getTableName(), getSearchCondition(),
                getSearchConditionArguments(theObj.getId()), values);
    }

    protected ClassToSave readData(Cursor theCursor) {
        return getObjectFromCursor(theCursor);
    }

    public static int getIntFromBool(boolean theValue) {
        return theValue ? 1 : 0;
    }

    public static boolean getBoolFromInt(int theValue) {
        return theValue >= 1;
    }
}
