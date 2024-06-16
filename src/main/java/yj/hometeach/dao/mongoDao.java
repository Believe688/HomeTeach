package yj.hometeach.dao;

import lombok.NonNull;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.List;

/**
 * @DATE 2024/6/10
 * @USER Linn
 */
public interface mongoDao {
    /*
   按照增删改查的顺序进行列举
    */
    // 增-插入数据
    Boolean insertDoc(Object object);

    // 改-根据条件更新数据
    Boolean updateDoc(Query query, Update update, Object object, Boolean isAll);

    // 查-查找数据
    Object findOneDoc(Query query, @NonNull Object object);
    // 查找全部数据
    List findAllDoc(Object object);
    // 根据条件获取数据
    List findByCondition(Query query, Class object);

    // 根据条件计算出所查询出的数据总数
    List getColumns(Aggregation agg, Object object);
}
