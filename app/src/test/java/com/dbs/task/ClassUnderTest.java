package com.dbs.task;

import android.content.Context;

public class ClassUnderTest {

    Context  context;
    public ClassUnderTest(Context context) {
        this.context = context;
    }
    public String getAppAsString(){
        return "DBSTask";
    }
}
