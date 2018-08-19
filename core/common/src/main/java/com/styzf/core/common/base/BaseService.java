package com.styzf.core.common.base;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface BaseService<D extends BaseDto> {

    /**
     * <p>
     * 插入一条记录（选择字段，策略插入）
     * </p>
     *
     * @param dto 实体对象
     * @return boolean
     */
    boolean baseInsert(D dto);

    /**
     * <p>
     * 插入一条记录（全部字段）
     * </p>
     *
     * @param dto 实体对象
     * @return boolean
     */
    boolean baseInsertAllColumn(D dto);

    /**
     * <p>
     * 插入（批量），该方法不适合 Oracle
     * </p>
     *
     * @param dtoList 实体对象列表
     * @return boolean
     */
    boolean baseInsertBatch(List<D> dtoList);

    /**
     * <p>
     * 插入（批量）
     * </p>
     *
     * @param dtoList 实体对象列表
     * @param batchSize  插入批次数量
     * @return boolean
     */
    boolean baseInsertBatch(List<D> dtoList, int batchSize);

    /**
     * <p>
     * 批量修改插入
     * </p>
     *
     * @param dtoList 实体对象列表
     * @return boolean
     */
    boolean baseInsertOrUpdateBatch(List<D> dtoList);

    /**
     * <p>
     * 批量修改插入
     * </p>
     *
     * @param dtoList 实体对象列表
     * @param batchSize
     * @return boolean
     */
    boolean baseInsertOrUpdateBatch(List<D> dtoList, int batchSize);

    /**
     * <p>
     * 批量修改或插入全部字段
     * </p>
     *
     * @param dtoList 实体对象列表
     * @return boolean
     */
    boolean baseInsertOrUpdateAllColumnBatch(List<D> dtoList);

    /**
     * 批量修改或插入全部字段
     *
     * @param dtoList 实体对象列表
     * @param batchSize
     * @return boolean
     */
    boolean baseInsertOrUpdateAllColumnBatch(List<D> dtoList, int batchSize);

    /**
     * <p>
     * 根据 ID 删除
     * </p>
     *
     * @param id 主键ID
     * @return boolean
     */
    boolean baseDeleteById(Serializable id);

    /**
     * <p>
     * 根据 columnMap 条件，删除记录
     * </p>
     *
     * @param columnMap 表字段 map 对象
     * @return boolean
     */
    boolean baseDeleteByMap(Map<String, Object> columnMap);

    /**
     * <p>
     * 删除（根据ID 批量删除）
     * </p>
     *
     * @param idList 主键ID列表
     * @return boolean
     */
    boolean baseDeleteBatchIds(Collection<? extends Serializable> idList);

    /**
     * <p>
     * 根据 ID 选择修改
     * </p>
     *
     * @param dto 实体对象
     * @return boolean
     */
    boolean baseUpdateById(D dto);

    /**
     * <p>
     * 根据 ID 修改全部字段
     * </p>
     *
     * @param dto 实体对象
     * @return boolean
     */
    boolean baseUpdateAllColumnById(D dto);

    /**
     * <p>
     * 根据ID 批量更新
     * </p>
     *
     * @param dtoList 实体对象列表
     * @return boolean
     */
    boolean baseUpdateBatchById(List<D> dtoList);

    /**
     * <p>
     * 根据ID 批量更新
     * </p>
     *
     * @param dtoList 实体对象列表
     * @param batchSize  更新批次数量
     * @return boolean
     */
    boolean baseUpdateBatchById(List<D> dtoList, int batchSize);

    /**
     * <p>
     * 根据ID 批量更新全部字段
     * </p>
     *
     * @param dtoList 实体对象列表
     * @return boolean
     */
    boolean baseUpdateAllColumnBatchById(List<D> dtoList);

    /**
     * <p>
     * 根据ID 批量更新全部字段
     * </p>
     *
     * @param dtoList 实体对象列表
     * @param batchSize  更新批次数量
     * @return boolean
     */
    boolean baseUpdateAllColumnBatchById(List<D> dtoList, int batchSize);

    /**
     * <p>
     * DableId 注解存在更新记录，否插入一条记录
     * </p>
     *
     * @param dto 实体对象
     * @return boolean
     */
    boolean baseInsertOrUpdate(D dto);

    /**
     * 插入或修改一条记录的全部字段
     *
     * @param dto 实体对象
     * @return boolean
     */
    boolean baseInsertOrUpdateAllColumn(D dto);

    /**
     * <p>
     * 根据 ID 查询
     * </p>
     *
     * @param id 主键ID
     * @return D
     */
    D baseSelectById(Serializable id);

    /**
     * <p>
     * 查询（根据ID 批量查询）
     * </p>
     *
     * @param idList 主键ID列表
     * @return List<D>
     */
    List<D> baseSelectBatchIds(Collection<? extends Serializable> idList);

    /**
     * <p>
     * 查询（根据 columnMap 条件）
     * </p>
     *
     * @param columnMap 表字段 map 对象
     * @return List<D>
     */
    List<D> baseSelectByMap(Map<String, Object> columnMap);

    /**
     * <p>
     * 翻页查询
     * </p>
     *
     * @param page 翻页对象
     * @return
     */
    PageDto<D> baseSelectPage(PageDto<D> page);
}
