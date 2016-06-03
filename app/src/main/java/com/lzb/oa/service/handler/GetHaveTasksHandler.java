package com.lzb.oa.service.handler;

import com.lzb.oa.entity.TaskInfoEntity;

import java.util.List;

/**
 * Created by lvzhenbin on 2016/6/3.
 */
public abstract class GetHaveTasksHandler {
    public abstract void success(List<TaskInfoEntity> entitys);
}
