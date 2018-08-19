package com.styzf.springboot.mybatisPlus.service.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.styzf.core.common.base.BaseDto;
import com.styzf.core.common.base.BaseService;
import com.styzf.core.common.base.PageDto;
import com.styzf.core.common.util.OrikaUtil;
import com.styzf.springboot.mybatisPlus.util.PageUtil;

public class BaseServiceImpl<T, D extends BaseDto, M extends BaseMapper<T>> extends ServiceImpl<M, T> implements BaseService<D>{
    
    protected Class<T> clazzT;
    protected Class<D> clazzD;
    
    public BaseServiceImpl() {
        ParameterizedType superclass = (ParameterizedType) this.getClass().getGenericSuperclass();
        Type[] types = superclass.getActualTypeArguments();
        this.clazzT = (Class<T>) types[0];
        this.clazzD = (Class<D>)types[1];
    }

    @Override
    public boolean baseInsert(D dto) {
        T t = OrikaUtil.map(dto, clazzT);
        return super.insert(t);
    }

    @Override
    public boolean baseInsertAllColumn(D dto) {
        T t = OrikaUtil.map(dto, clazzT);
        return super.insertAllColumn(t);
    }

    @Override
    public boolean baseInsertBatch(List<D> dtoList) {
        List<T> mapList = OrikaUtil.mapList(dtoList, clazzT);
        return super.insertBatch(mapList);
    }

    @Override
    public boolean baseInsertBatch(List<D> dtoList, int batchSize) {
        List<T> mapList = OrikaUtil.mapList(dtoList, clazzT);
        return super.insertBatch(mapList, batchSize);
    }

    @Override
    public boolean baseInsertOrUpdateBatch(List<D> dtoList) {
        List<T> mapList = OrikaUtil.mapList(dtoList, clazzT);
        return super.insertOrUpdateBatch(mapList);
    }

    @Override
    public boolean baseInsertOrUpdateBatch(List<D> dtoList, int batchSize) {
        List<T> mapList = OrikaUtil.mapList(dtoList, clazzT);
        return super.insertOrUpdateBatch(mapList, batchSize);
    }

    @Override
    public boolean baseInsertOrUpdateAllColumnBatch(List<D> dtoList) {
        List<T> mapList = OrikaUtil.mapList(dtoList, clazzT);
        return super.insertOrUpdateAllColumnBatch(mapList);
    }

    @Override
    public boolean baseInsertOrUpdateAllColumnBatch(List<D> dtoList, int batchSize) {
        List<T> mapList = OrikaUtil.mapList(dtoList, clazzT);
        return super.insertOrUpdateAllColumnBatch(mapList, batchSize);
    }

    @Override
    public boolean baseDeleteById(Serializable id) {
        return super.deleteById(id);
    }

    @Override
    public boolean baseDeleteByMap(Map<String, Object> columnMap) {
        return super.deleteByMap(columnMap);
    }

    @Override
    public boolean baseDeleteBatchIds(Collection<? extends Serializable> idList) {
        return super.deleteBatchIds(idList);
    }

    @Override
    public boolean baseUpdateById(D dto) {
        T t = OrikaUtil.map(dto, clazzT);
        return super.updateById(t);
    }

    @Override
    public boolean baseUpdateAllColumnById(D dto) {
        T t = OrikaUtil.map(dto, clazzT);
        return super.updateAllColumnById(t);
    }

    @Override
    public boolean baseUpdateBatchById(List<D> dtoList) {
        List<T> mapList = OrikaUtil.mapList(dtoList, clazzT);
        return super.updateBatchById(mapList);
    }

    @Override
    public boolean baseUpdateBatchById(List<D> dtoList, int batchSize) {
        List<T> mapList = OrikaUtil.mapList(dtoList, clazzT);
        return super.updateBatchById(mapList, batchSize);
    }

    @Override
    public boolean baseUpdateAllColumnBatchById(List<D> dtoList) {
        List<T> mapList = OrikaUtil.mapList(dtoList, clazzT);
        return super.updateAllColumnBatchById(mapList);
    }

    @Override
    public boolean baseUpdateAllColumnBatchById(List<D> dtoList, int batchSize) {
        List<T> mapList = OrikaUtil.mapList(dtoList, clazzT);
        return super.updateAllColumnBatchById(mapList, batchSize);
    }

    @Override
    public boolean baseInsertOrUpdate(D dto) {
        T t = OrikaUtil.map(dto, clazzT);
        return super.insertOrUpdate(t);
    }

    @Override
    public boolean baseInsertOrUpdateAllColumn(D dto) {
        T t = OrikaUtil.map(dto, clazzT);
        return super.insertOrUpdateAllColumn(t);
    }

    @Override
    public D baseSelectById(Serializable id) {
        T t = super.selectById(id);
        return OrikaUtil.map(t, clazzD);
    }

    @Override
    public List<D> baseSelectBatchIds(Collection<? extends Serializable> idList) {
        List<T> tList = super.selectBatchIds(idList);
        return OrikaUtil.mapList(tList, clazzD);
    }

    @Override
    public List<D> baseSelectByMap(Map<String, Object> columnMap) {
        List<T> tList = super.selectByMap(columnMap);
        return OrikaUtil.mapList(tList, clazzD);
    }

    @Override
    public PageDto<D> baseSelectPage(PageDto<D> pageDto) {
        Page<T> page = super.selectPage(
                new Page<>(pageDto.getPage(), pageDto.getPageSize(), pageDto.getOrderBy(), pageDto.isAscSort()));
        return PageUtil.mapPage(page, clazzD);
    }
    
}
