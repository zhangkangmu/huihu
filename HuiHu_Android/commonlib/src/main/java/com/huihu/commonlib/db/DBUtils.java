package com.huihu.commonlib.db;

import android.database.Cursor;
import android.util.Log;

import org.xutils.DbManager;
import org.xutils.common.util.KeyValue;
import org.xutils.db.Selector;
import org.xutils.ex.DbException;
import org.xutils.x;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by wenjiarong on 2018/10/11 0011.
 */
public class DBUtils<T> {

    private Selector<T> selector;
    private String dbName;
    private Class<T> entityType;

    private DBUtils() {
    }

    //删除数据库
    public void dropDB() {
        try {
            getDB(dbName).dropDb();
        } catch (DbException e) {
            Log.e("@@@", e.getMessage());
        }
    }

    //删除表
    public void dropTable() {
        try {
            getDB(dbName).dropTable(entityType);
        } catch (DbException e) {
            Log.e("@@@", e.getMessage());
        }
    }

    //增
    public void add(Object optData) {
        try {
            getDB(dbName).save(optData);
        } catch (DbException e) {
            Log.e("@@@", e.getMessage());
        }
    }

    //删，根据指定数据删除
    public void delete(Object optData) {
        try {
            getDB(dbName).delete(optData);
        } catch (DbException e) {
            Log.e("@@@", e.getMessage());
        }
    }

    //删，根据where子句删除
    public void delete() {
        try {
            getDB(dbName).delete(entityType, selector.getWhereBuilder());
        } catch (DbException e) {
            Log.e("@@@", e.getMessage());
        }
    }

    //改，没有就增
    public void update(Object optData) {
        try {
            getDB(dbName).saveOrUpdate(optData);
        } catch (DbException e) {
            Log.e("@@@", e.getMessage());
        }
    }

    //改部分列
    public int update(String[] keys, Object[] values) {
        KeyValue[] keyValues = null;
        if (keys != null && values != null && keys.length == values.length) {
            keyValues = new KeyValue[keys.length];
            for (int i = 0; i < keys.length; i++) {
                keyValues[i] = new KeyValue(keys[i], values[i]);
            }
        }

        if (keyValues != null && keyValues.length != 0) {
            try {
                return getDB(dbName).update(entityType, selector.getWhereBuilder(), keyValues);
            } catch (DbException e) {
                Log.e("@@@", e.getMessage());
            }
        }
        return 0;
    }

    //查找
    public List<T> search() {
        List<T> retList = null;
        try {
//            retList = new ArrayList<>();
            retList = selector.findAll();

        } catch (DbException e) {
            Log.e("@@@", e.getMessage());
        }
        return retList==null?new ArrayList<T>():retList;
    }

    //查找第一个
    public T searchFirst() {
        try {
            return selector.findFirst();
        } catch (DbException e) {
            Log.e("@@@", e.getMessage());
        }
        return null;
    }

    /**
     * 直接提供一个sql语句的操作，方便遇到函数无法解决的问题时，可以直接采用SQL语句来操作
     *
     * @param bQueryType 是否是查询的sql
     * @param dbName     数据库名字
     * @param sql        执行的sql
     * @return           原生数据库游标
     */
    public static Cursor execSqlQuery(boolean bQueryType, String dbName, String sql) {
        try {
            if (bQueryType) {
                return getDB(dbName).execQuery(sql);
            } else {
                getDB(dbName).execNonQuery(sql);
            }
        } catch (DbException e) {
            Log.e("@@@", e.getMessage());
        }

        return null;
    }

    /**
     * 通过类型获取该类型在数据库中映射的表名
     *
     * @param dbName        数据库名
     * @param entityType    实体类对象
     * @return              该实体类对象在数据库中对应的表名
     */
    public static String getTableName(String dbName, Class<?> entityType) {
        try {
            return getDB(dbName).getTable(entityType).getName();
        } catch (DbException e) {
            Log.e("@@@", e.getMessage());
        }
        return null;
    }

    //region 【配置DB】
    //内部操作，为了简化结构，不再多创建一个文件了
    private static HashMap<String, DbManager.DaoConfig> m_DbConfigMap = new HashMap<>();
    private static String m_strDbDir = null;

    private static DbManager.DaoConfig getDaoConfig(String dbName) {
        return m_DbConfigMap.get(dbName);
    }

    private static void setDaoConfig(String dbName, DbManager.DaoConfig daoConfig) {
        m_DbConfigMap.put(dbName, daoConfig);
    }

    public static void config(String DBdir) {
        m_strDbDir = DBdir;
    }

    private static DbManager.DaoConfig newDaoConfig(String dbName) {
        DbManager.DaoConfig daoConfig = new DbManager.DaoConfig()
                .setDbName(dbName)
                //.setDbDir(new File("/sdcard/0test/")) //不设置dbDir时，默认存储在app的私有目录中
                .setDbVersion(1)
                .setDbOpenListener(new DbManager.DbOpenListener() {
                    @Override
                    public void onDbOpened(DbManager db) {
                        //开启WAL，据说可以大幅度提升写入速度
                        db.getDatabase().enableWriteAheadLogging();
                    }
                })
                .setDbUpgradeListener(new DbManager.DbUpgradeListener() {
                    @Override
                    public void onUpgrade(DbManager db, int oldVersion, int newVersion) {
                        // TODO: ...
                        //addColumn;dropTable;dropDb
                    }
                });

        if (m_strDbDir != null) {
            daoConfig.setDbDir(new File(m_strDbDir));
        }

        return daoConfig;
    }

    private static DbManager getDB(String dbName) throws DbException {
        DbManager retDB = null;
        DbManager.DaoConfig daoConfig = getDaoConfig(dbName);
        if (null == daoConfig) {
            daoConfig = newDaoConfig(dbName);
            setDaoConfig(dbName, daoConfig);
        }

        if (null != daoConfig) {
            retDB = x.getDb(daoConfig);
        }

        if (null == retDB) {
            throw new DbException("DbManager is empty.");
        }

        return retDB;
    }
    //endregion


    public static class Builder<T> {
        private DBUtils<T> dbUtils = new DBUtils<>();

        public Builder(String dbName, Class<T> entityType) {
            try {
                dbUtils.dbName = dbName;
                dbUtils.entityType = entityType;
                dbUtils.selector = getDB(dbName).selector(entityType);
            } catch (DbException e) {
                Log.e("@@@", e.getMessage());
            }
        }

        public DBUtils<T> build() {
            return dbUtils;
        }

        public Builder<T> where(String columnName, String op, Object value) {
            this.dbUtils.selector.where(columnName, op, value);
            return this;
        }

        public Builder<T> and(String columnName, String op, Object value) {
            this.dbUtils.selector.and(columnName, op, value);
            return this;
        }

        public Builder<T> or(String columnName, String op, Object value) {
            this.dbUtils.selector.or(columnName, op, value);
            return this;
        }

        public Builder<T> expr(String expr) {
            this.dbUtils.selector.expr(expr);
            return this;
        }

        public Builder<T> orderBy(String columnName) {
            this.dbUtils.selector.orderBy(columnName);
            return this;
        }

        public Builder<T> orderBy(String columnName, boolean desc) {
            this.dbUtils.selector.orderBy(columnName, desc);
            return this;
        }

        public Builder<T> limit(int limit) {
            this.dbUtils.selector.limit(limit);
            return this;
        }

        public Builder<T> offset(int offset) {
            this.dbUtils.selector.offset(offset);
            return this;
        }

        @Override
        public String toString() {
            return this.dbUtils.selector.toString();
        }
    }


}
