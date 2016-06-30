package com.lzb.oa.service.handler;

import com.lzb.oa.entity.TaskInfoEntity;

import java.util.List;

/**
 * Created by lvzhenbin on 2016/4/3.
 * 取消任务信息回调
 */
public abstract class GetTaskInfosHandler {
    public abstract void onSuccess(List<TaskInfoEntity> taskEntity);
}
