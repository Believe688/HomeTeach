package yj.hometeach.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoException;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @TIME 2024/6/12
 * @USER Linn
 */
@Slf4j
@Service
public class mongoDaoImpl implements mongoDao{
    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 插入数据
     *
     * @param object 插入的对象
     * @return 是否插入成功
     */
    @Override
    public Boolean insertDoc(@NonNull Object object) {
        return !ObjectUtils.isEmpty(mongoTemplate.save(object));
    }

    @Override
    public Object findOneDoc(Query query, @NonNull Object object) {
        return mongoTemplate.findOne(query, object.getClass());
    }

    @Override
    public List findAllDoc(@NonNull Object object) {
        return mongoTemplate.findAll(object.getClass());
    }

    /**
     * 根据条件查询
     *
     * @param query  查找条件
     * @param object 查找集合
     * @return 查询结果
     */
    @Override
    public List findByCondition(Query query, @NonNull Class object) {

        try {
            return mongoTemplate.find(query, object);
        } catch (Exception e) {
            log.error("Error finding documents--dao:", e);
            throw new RuntimeException("Finding has something Error");
        }
    }

    /**
     * 更新数据
     *
     * @param query  查询条件
     * @param update 更新的条件
     * @param object 更新的对象
     * @param isAll  是否对符合条件的全更新
     * @return 是否更新成功
     */
    @Override
    public Boolean updateDoc(Query query, Update update, @NonNull Object object, Boolean isAll) {
        try {
            if (isAll) {
                mongoTemplate.updateMulti(query, update, object.getClass());
            } else {
                mongoTemplate.updateFirst(query, update, object.getClass());
            }
        } catch (Exception e) {
            log.error("Error updating document", e);
            return false;
        }
        return true;
    }

    /**
     * 聚合查询，聚合统计某列的值
     *
     * @param agg    聚合条件
     * @param object 聚合返回值类型
     * @return 聚合结果
     */
    @Override
    public List getColumns(Aggregation agg, @NonNull Object object) {
        try {
            return mongoTemplate.aggregate(agg, object.getClass(), BasicDBObject.class).getMappedResults();
        } catch (MongoException e) {
            log.error("Error getColumns:", e);
            return null;
        }
    }
}
