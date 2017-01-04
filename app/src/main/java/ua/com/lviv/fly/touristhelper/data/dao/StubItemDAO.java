package ua.com.lviv.fly.touristhelper.data.dao;

import android.content.ContentValues;
import android.database.Cursor;

import ua.com.lviv.fly.touristhelper.data.ResultsVO;
import ua.com.lviv.fly.touristhelper.data.base.AbstractDAO;

/**
 * Created on 22.05.2015.
 */
public class StubItemDAO extends AbstractDAO<ResultsVO, String> {

    private final static String TABLE_NAME = "results";
    private final static String COLUMN_ID = "_id";
    private final static String COLUMN_ICON = "icon";
    private final static String COLUMN_NAME = "name";

    @Override
    protected String getSearchCondition() {
        return COLUMN_ID + " = ?";
    }

    @Override
    protected String[] getSearchConditionArguments(String theId) {
        return new String[]{theId};
    }

    @Override
    protected String getTableName() {
        return TABLE_NAME;
    }

    @Override
    protected void putObjectDataForSaving(ContentValues theValues, ResultsVO theObj) {
        theValues.put(COLUMN_ID, theObj.getId());
        putObjectDataForUpdate(theValues, theObj);
    }

    @Override
    protected void putObjectDataForUpdate(ContentValues theValues, ResultsVO theObj) {
        theValues.put(COLUMN_ICON, theObj.getIcon());
        theValues.put(COLUMN_NAME, theObj.getName());
}

    @Override
    protected String[] getKeyColumns() {
        return new String[]{COLUMN_ID};
    }

    @Override
    protected ResultsVO getObjectFromCursor(Cursor theCursor) {
        int columnId = theCursor.getColumnIndex(COLUMN_ID);
        int icon = theCursor.getColumnIndex(COLUMN_ICON);
        int name = theCursor.getColumnIndex(COLUMN_NAME);

        ResultsVO item = new ResultsVO();
        item.setId(theCursor.getString(columnId));
        item.setIcon(theCursor.getString(icon));
        item.setName(theCursor.getString(name));

        return item;
    }
}
