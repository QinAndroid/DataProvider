package com.qzk.library.query;

import com.qzk.library.Exception.ErrorMessageException;

/**
 * 类名：QueryDo
 * 描述：
 * 包名： com.qzk.library.query
 * 项目名：DataProvider
 * Created by qinzongke on 6/23/16.
 */
public class QueryDo {
    private static final String AND = " and ";
    private static final String OR = " or ";
    private static final String EQUAL = "=";
    private static final String NOTEQUAL = "!=";
    private static final String LIKE = " like ";
    private static final String LIKESYSBOM = "%";
    private static final String SQUOTE = "'";//'单引号
    private StringBuffer sql;
    private boolean isOr = false;

    public QueryDo(){
        sql = new StringBuffer();

    }

    public QueryDo(StringBuffer sb){
        sql = sb;
    }
    /**
     * 查询 =
     * @param fieldName
     * @param value
     * @return
     */
    public QueryDo equal(String fieldName,Object value){
        try{
            appendAndOR();
            sql.append(fieldName).append(EQUAL);
            if(value instanceof String){
                sql.append(SQUOTE).append(value).append(SQUOTE);
            }else if((value instanceof Integer) ||(value instanceof Long)||(value instanceof Float)||(value instanceof Double)){
                sql.append(value);
            }else{
                throw new ErrorMessageException("Query->equal->参数数据类型异常");
            }
            resetIsOr();
        }catch (ErrorMessageException e){
            e.printStackTrace();
        }
        return this;
    }

    /**
     * notEqual !=
     * @param fieldName
     * @param value
     * @return
     */
    public QueryDo notEqual(String fieldName,String value){
        appendAndOR();
        sql.append(fieldName).append(NOTEQUAL).append(SQUOTE).append(value).append(SQUOTE);
        resetIsOr();
        return this;
    }

    /**
     * like like '%str%'
     * @param fieldName
     * @param value
     * @return
     */
    public QueryDo like(String fieldName,String value){
        appendAndOR();
        sql.append(fieldName).append(LIKE).append(SQUOTE).append(LIKESYSBOM).append(value).append(LIKESYSBOM).append(SQUOTE);
        resetIsOr();
        return this;
    }


    /**
     * 判断查询条件是and还是or
     */
    private void appendAndOR(){
        if(isOr){
            sql.append(OR);
        }else{
            sql.append(AND);
        }
    }

    /**
     * 每次添加条件后 将or改变为and
     * 再次添加条件默认为and
     */
    private void resetIsOr(){
        if(isOr){
            isOr = false;
        }
    }

    /**
     * 默认查询条件为and 修改条件为or
     * @return
     */
    public QueryDo or(){
        isOr = true;
        return this;
    }


}
