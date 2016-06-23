package com.qzk.library.query;

import com.qzk.library.Exception.ErrorMessageException;
import com.qzk.library.enums.QuerySortType;

/**
 * 类名：Query
 * 描述：
 * 包名： com.qzk.library.condition
 * 项目名：DataProvider
 * Created by qinzongke on 6/23/16.
 */
public class Query {

    private StringBuffer sql = new StringBuffer();


    private static final String SELECT = "select ";
    private static final String SELECTALL = " * ";
    private static final String FROM = " from ";
    private static final String WHERE = " where 1=1 ";
    private static final String AND = " and ";
    private static final String OR = " or ";
    private static final String EQUAL = "=";
    private static final String NOTEQUAL = "!=";
    private static final String LIKE = " like ";
    private static final String LIKESYSBOM = "%";
    private static final String SQUOTE = "'";//'单引号
    private static final String COMMA = ",";//,逗号
    private static final String ORDERBY = " order by ";
    private static final String ASCENDING = " ASC ";//升序
    private static final String DESCENDING = " DESC ";//降序
    private static final String LIMIT = " limit ";


    private Class clazz;
    private StringBuffer sortString = new StringBuffer();
    private StringBuffer limitString = new StringBuffer();




    public QueryDo from(Class clazz,String... fieldsName){
        this.setClazz(clazz);
        String tableName = clazz.getSimpleName().toLowerCase().toString();
        String selectFields = createSelectFields(fieldsName);
        sql.append(SELECT).append(selectFields).append(FROM).append(tableName).append(WHERE);

        return new QueryDo(sql);
    }


//   public class QueryDo{

//        /**
//         * 查询 =
//         * @param fieldName
//         * @param value
//         * @return
//         */
//        public QueryDo equal(String fieldName,Object value){
//            try{
//                appendAndOR();
//                sql.append(fieldName).append(EQUAL);
//                if(value instanceof String){
//                    sql.append(SQUOTE).append(value).append(SQUOTE);
//                }else if((value instanceof Integer) ||(value instanceof Long)||(value instanceof Float)||(value instanceof Double)){
//                    sql.append(value);
//                }else{
//                    throw new ErrorMessageException("Query->equal->参数数据类型异常");
//                }
//                resetIsOr();
//            }catch (ErrorMessageException e){
//                e.printStackTrace();
//            }
//            return this;
//        }
//
//        /**
//         * notEqual !=
//         * @param fieldName
//         * @param value
//         * @return
//         */
//        public QueryDo notEqual(String fieldName,String value){
//            appendAndOR();
//            sql.append(fieldName).append(NOTEQUAL).append(SQUOTE).append(value).append(SQUOTE);
//            resetIsOr();
//            return this;
//        }
//
//        /**
//         * like like '%str%'
//         * @param fieldName
//         * @param value
//         * @return
//         */
//        public QueryDo like(String fieldName,String value){
//            appendAndOR();
//            sql.append(fieldName).append(LIKE).append(SQUOTE).append(LIKESYSBOM).append(value).append(LIKESYSBOM).append(SQUOTE);
//            resetIsOr();
//            return this;
//        }
//
//
//        /**
//         * 判断查询条件是and还是or
//         */
//        private void appendAndOR(){
//            if(isOr){
//                sql.append(OR);
//            }else{
//                sql.append(AND);
//            }
//        }
//
//        /**
//         * 每次添加条件后 将or改变为and
//         * 再次添加条件默认为and
//         */
//        private void resetIsOr(){
//            if(isOr){
//                isOr = false;
//            }
//        }
//
//        /**
//         * 默认查询条件为and 修改条件为or
//         * @return
//         */
//        public QueryDo or(){
//            isOr = true;
//            return this;
//        }
//






//    }




    /**
     * 排序
     */
    public void sort(QuerySort ... sorts){
        sortString.append(ORDERBY);
        for (QuerySort sort : sorts){
            if(null == sort){
                continue;
            }

            sortString.append(sort.getFieldsName()).append(getSort(sort.getType())).append(COMMA);
        }
        sortString = new StringBuffer(sortString.subSequence(0,sortString.length()-1));
        setSortString(sortString);
    }

    /**
     * limit
     * @param start
     * @param end
     */
    public void limit(int start,int end){
        limitString.append(LIMIT).append(start).append(COMMA).append(end);
        setLimitString(limitString);
    }
    /**
     * 获取排序类型
     * @param type
     * @return
     */
    private  String getSort(QuerySortType type){
        if(QuerySortType.ASCENDING == type){
            return ASCENDING;
        }else if (QuerySortType.DESCENDING == type){
            return DESCENDING;
        }
        return ASCENDING;
    }

    /**
     * 生成查询sql
     * @return
     */
    public StringBuffer createSqlQuery(){
        return sql;
    }




    /**
     * 组合查询字段
     * @param fields
     * @return
     */
    private  String createSelectFields(String... fields) {
        StringBuffer sb = new StringBuffer();
        for (String field : fields) {
            if(field == null || field.equals("")){
                continue;
            }
            sb.append(field).append(",");
        }
        String selectFields = SELECTALL;
        if(sb.length()>1){
            selectFields = sb.subSequence(0, sb.length() - 1).toString();
        }
        return selectFields;
    }

    public Class getClazz() {
        return clazz;
    }

    private void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public StringBuffer getSortString() {
        return sortString;
    }

    private void setSortString(StringBuffer sortString) {
        this.sortString = sortString;
    }

    public StringBuffer getLimitString() {
        return limitString;
    }

    private void setLimitString(StringBuffer limitString) {
        this.limitString = limitString;
    }
}
