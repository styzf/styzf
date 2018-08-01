package com.styzf.test.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StyzfTestMainExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public StyzfTestMainExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andTestIdIsNull() {
            addCriterion("test_id is null");
            return (Criteria) this;
        }

        public Criteria andTestIdIsNotNull() {
            addCriterion("test_id is not null");
            return (Criteria) this;
        }

        public Criteria andTestIdEqualTo(String value) {
            addCriterion("test_id =", value, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdNotEqualTo(String value) {
            addCriterion("test_id <>", value, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdGreaterThan(String value) {
            addCriterion("test_id >", value, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdGreaterThanOrEqualTo(String value) {
            addCriterion("test_id >=", value, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdLessThan(String value) {
            addCriterion("test_id <", value, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdLessThanOrEqualTo(String value) {
            addCriterion("test_id <=", value, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdLike(String value) {
            addCriterion("test_id like", value, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdNotLike(String value) {
            addCriterion("test_id not like", value, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdIn(List<String> values) {
            addCriterion("test_id in", values, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdNotIn(List<String> values) {
            addCriterion("test_id not in", values, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdBetween(String value1, String value2) {
            addCriterion("test_id between", value1, value2, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdNotBetween(String value1, String value2) {
            addCriterion("test_id not between", value1, value2, "testId");
            return (Criteria) this;
        }

        public Criteria andTestRelIdIsNull() {
            addCriterion("test_rel_id is null");
            return (Criteria) this;
        }

        public Criteria andTestRelIdIsNotNull() {
            addCriterion("test_rel_id is not null");
            return (Criteria) this;
        }

        public Criteria andTestRelIdEqualTo(String value) {
            addCriterion("test_rel_id =", value, "testRelId");
            return (Criteria) this;
        }

        public Criteria andTestRelIdNotEqualTo(String value) {
            addCriterion("test_rel_id <>", value, "testRelId");
            return (Criteria) this;
        }

        public Criteria andTestRelIdGreaterThan(String value) {
            addCriterion("test_rel_id >", value, "testRelId");
            return (Criteria) this;
        }

        public Criteria andTestRelIdGreaterThanOrEqualTo(String value) {
            addCriterion("test_rel_id >=", value, "testRelId");
            return (Criteria) this;
        }

        public Criteria andTestRelIdLessThan(String value) {
            addCriterion("test_rel_id <", value, "testRelId");
            return (Criteria) this;
        }

        public Criteria andTestRelIdLessThanOrEqualTo(String value) {
            addCriterion("test_rel_id <=", value, "testRelId");
            return (Criteria) this;
        }

        public Criteria andTestRelIdLike(String value) {
            addCriterion("test_rel_id like", value, "testRelId");
            return (Criteria) this;
        }

        public Criteria andTestRelIdNotLike(String value) {
            addCriterion("test_rel_id not like", value, "testRelId");
            return (Criteria) this;
        }

        public Criteria andTestRelIdIn(List<String> values) {
            addCriterion("test_rel_id in", values, "testRelId");
            return (Criteria) this;
        }

        public Criteria andTestRelIdNotIn(List<String> values) {
            addCriterion("test_rel_id not in", values, "testRelId");
            return (Criteria) this;
        }

        public Criteria andTestRelIdBetween(String value1, String value2) {
            addCriterion("test_rel_id between", value1, value2, "testRelId");
            return (Criteria) this;
        }

        public Criteria andTestRelIdNotBetween(String value1, String value2) {
            addCriterion("test_rel_id not between", value1, value2, "testRelId");
            return (Criteria) this;
        }

        public Criteria andTestNameIsNull() {
            addCriterion("test_name is null");
            return (Criteria) this;
        }

        public Criteria andTestNameIsNotNull() {
            addCriterion("test_name is not null");
            return (Criteria) this;
        }

        public Criteria andTestNameEqualTo(String value) {
            addCriterion("test_name =", value, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameNotEqualTo(String value) {
            addCriterion("test_name <>", value, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameGreaterThan(String value) {
            addCriterion("test_name >", value, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameGreaterThanOrEqualTo(String value) {
            addCriterion("test_name >=", value, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameLessThan(String value) {
            addCriterion("test_name <", value, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameLessThanOrEqualTo(String value) {
            addCriterion("test_name <=", value, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameLike(String value) {
            addCriterion("test_name like", value, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameNotLike(String value) {
            addCriterion("test_name not like", value, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameIn(List<String> values) {
            addCriterion("test_name in", values, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameNotIn(List<String> values) {
            addCriterion("test_name not in", values, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameBetween(String value1, String value2) {
            addCriterion("test_name between", value1, value2, "testName");
            return (Criteria) this;
        }

        public Criteria andTestNameNotBetween(String value1, String value2) {
            addCriterion("test_name not between", value1, value2, "testName");
            return (Criteria) this;
        }

        public Criteria andTestAgeIsNull() {
            addCriterion("test_age is null");
            return (Criteria) this;
        }

        public Criteria andTestAgeIsNotNull() {
            addCriterion("test_age is not null");
            return (Criteria) this;
        }

        public Criteria andTestAgeEqualTo(Integer value) {
            addCriterion("test_age =", value, "testAge");
            return (Criteria) this;
        }

        public Criteria andTestAgeNotEqualTo(Integer value) {
            addCriterion("test_age <>", value, "testAge");
            return (Criteria) this;
        }

        public Criteria andTestAgeGreaterThan(Integer value) {
            addCriterion("test_age >", value, "testAge");
            return (Criteria) this;
        }

        public Criteria andTestAgeGreaterThanOrEqualTo(Integer value) {
            addCriterion("test_age >=", value, "testAge");
            return (Criteria) this;
        }

        public Criteria andTestAgeLessThan(Integer value) {
            addCriterion("test_age <", value, "testAge");
            return (Criteria) this;
        }

        public Criteria andTestAgeLessThanOrEqualTo(Integer value) {
            addCriterion("test_age <=", value, "testAge");
            return (Criteria) this;
        }

        public Criteria andTestAgeIn(List<Integer> values) {
            addCriterion("test_age in", values, "testAge");
            return (Criteria) this;
        }

        public Criteria andTestAgeNotIn(List<Integer> values) {
            addCriterion("test_age not in", values, "testAge");
            return (Criteria) this;
        }

        public Criteria andTestAgeBetween(Integer value1, Integer value2) {
            addCriterion("test_age between", value1, value2, "testAge");
            return (Criteria) this;
        }

        public Criteria andTestAgeNotBetween(Integer value1, Integer value2) {
            addCriterion("test_age not between", value1, value2, "testAge");
            return (Criteria) this;
        }

        public Criteria andAttribute1IsNull() {
            addCriterion("attribute1 is null");
            return (Criteria) this;
        }

        public Criteria andAttribute1IsNotNull() {
            addCriterion("attribute1 is not null");
            return (Criteria) this;
        }

        public Criteria andAttribute1EqualTo(String value) {
            addCriterion("attribute1 =", value, "attribute1");
            return (Criteria) this;
        }

        public Criteria andAttribute1NotEqualTo(String value) {
            addCriterion("attribute1 <>", value, "attribute1");
            return (Criteria) this;
        }

        public Criteria andAttribute1GreaterThan(String value) {
            addCriterion("attribute1 >", value, "attribute1");
            return (Criteria) this;
        }

        public Criteria andAttribute1GreaterThanOrEqualTo(String value) {
            addCriterion("attribute1 >=", value, "attribute1");
            return (Criteria) this;
        }

        public Criteria andAttribute1LessThan(String value) {
            addCriterion("attribute1 <", value, "attribute1");
            return (Criteria) this;
        }

        public Criteria andAttribute1LessThanOrEqualTo(String value) {
            addCriterion("attribute1 <=", value, "attribute1");
            return (Criteria) this;
        }

        public Criteria andAttribute1Like(String value) {
            addCriterion("attribute1 like", value, "attribute1");
            return (Criteria) this;
        }

        public Criteria andAttribute1NotLike(String value) {
            addCriterion("attribute1 not like", value, "attribute1");
            return (Criteria) this;
        }

        public Criteria andAttribute1In(List<String> values) {
            addCriterion("attribute1 in", values, "attribute1");
            return (Criteria) this;
        }

        public Criteria andAttribute1NotIn(List<String> values) {
            addCriterion("attribute1 not in", values, "attribute1");
            return (Criteria) this;
        }

        public Criteria andAttribute1Between(String value1, String value2) {
            addCriterion("attribute1 between", value1, value2, "attribute1");
            return (Criteria) this;
        }

        public Criteria andAttribute1NotBetween(String value1, String value2) {
            addCriterion("attribute1 not between", value1, value2, "attribute1");
            return (Criteria) this;
        }

        public Criteria andAttribute2IsNull() {
            addCriterion("attribute2 is null");
            return (Criteria) this;
        }

        public Criteria andAttribute2IsNotNull() {
            addCriterion("attribute2 is not null");
            return (Criteria) this;
        }

        public Criteria andAttribute2EqualTo(String value) {
            addCriterion("attribute2 =", value, "attribute2");
            return (Criteria) this;
        }

        public Criteria andAttribute2NotEqualTo(String value) {
            addCriterion("attribute2 <>", value, "attribute2");
            return (Criteria) this;
        }

        public Criteria andAttribute2GreaterThan(String value) {
            addCriterion("attribute2 >", value, "attribute2");
            return (Criteria) this;
        }

        public Criteria andAttribute2GreaterThanOrEqualTo(String value) {
            addCriterion("attribute2 >=", value, "attribute2");
            return (Criteria) this;
        }

        public Criteria andAttribute2LessThan(String value) {
            addCriterion("attribute2 <", value, "attribute2");
            return (Criteria) this;
        }

        public Criteria andAttribute2LessThanOrEqualTo(String value) {
            addCriterion("attribute2 <=", value, "attribute2");
            return (Criteria) this;
        }

        public Criteria andAttribute2Like(String value) {
            addCriterion("attribute2 like", value, "attribute2");
            return (Criteria) this;
        }

        public Criteria andAttribute2NotLike(String value) {
            addCriterion("attribute2 not like", value, "attribute2");
            return (Criteria) this;
        }

        public Criteria andAttribute2In(List<String> values) {
            addCriterion("attribute2 in", values, "attribute2");
            return (Criteria) this;
        }

        public Criteria andAttribute2NotIn(List<String> values) {
            addCriterion("attribute2 not in", values, "attribute2");
            return (Criteria) this;
        }

        public Criteria andAttribute2Between(String value1, String value2) {
            addCriterion("attribute2 between", value1, value2, "attribute2");
            return (Criteria) this;
        }

        public Criteria andAttribute2NotBetween(String value1, String value2) {
            addCriterion("attribute2 not between", value1, value2, "attribute2");
            return (Criteria) this;
        }

        public Criteria andAttribute3IsNull() {
            addCriterion("attribute3 is null");
            return (Criteria) this;
        }

        public Criteria andAttribute3IsNotNull() {
            addCriterion("attribute3 is not null");
            return (Criteria) this;
        }

        public Criteria andAttribute3EqualTo(String value) {
            addCriterion("attribute3 =", value, "attribute3");
            return (Criteria) this;
        }

        public Criteria andAttribute3NotEqualTo(String value) {
            addCriterion("attribute3 <>", value, "attribute3");
            return (Criteria) this;
        }

        public Criteria andAttribute3GreaterThan(String value) {
            addCriterion("attribute3 >", value, "attribute3");
            return (Criteria) this;
        }

        public Criteria andAttribute3GreaterThanOrEqualTo(String value) {
            addCriterion("attribute3 >=", value, "attribute3");
            return (Criteria) this;
        }

        public Criteria andAttribute3LessThan(String value) {
            addCriterion("attribute3 <", value, "attribute3");
            return (Criteria) this;
        }

        public Criteria andAttribute3LessThanOrEqualTo(String value) {
            addCriterion("attribute3 <=", value, "attribute3");
            return (Criteria) this;
        }

        public Criteria andAttribute3Like(String value) {
            addCriterion("attribute3 like", value, "attribute3");
            return (Criteria) this;
        }

        public Criteria andAttribute3NotLike(String value) {
            addCriterion("attribute3 not like", value, "attribute3");
            return (Criteria) this;
        }

        public Criteria andAttribute3In(List<String> values) {
            addCriterion("attribute3 in", values, "attribute3");
            return (Criteria) this;
        }

        public Criteria andAttribute3NotIn(List<String> values) {
            addCriterion("attribute3 not in", values, "attribute3");
            return (Criteria) this;
        }

        public Criteria andAttribute3Between(String value1, String value2) {
            addCriterion("attribute3 between", value1, value2, "attribute3");
            return (Criteria) this;
        }

        public Criteria andAttribute3NotBetween(String value1, String value2) {
            addCriterion("attribute3 not between", value1, value2, "attribute3");
            return (Criteria) this;
        }

        public Criteria andAttribute4IsNull() {
            addCriterion("attribute4 is null");
            return (Criteria) this;
        }

        public Criteria andAttribute4IsNotNull() {
            addCriterion("attribute4 is not null");
            return (Criteria) this;
        }

        public Criteria andAttribute4EqualTo(String value) {
            addCriterion("attribute4 =", value, "attribute4");
            return (Criteria) this;
        }

        public Criteria andAttribute4NotEqualTo(String value) {
            addCriterion("attribute4 <>", value, "attribute4");
            return (Criteria) this;
        }

        public Criteria andAttribute4GreaterThan(String value) {
            addCriterion("attribute4 >", value, "attribute4");
            return (Criteria) this;
        }

        public Criteria andAttribute4GreaterThanOrEqualTo(String value) {
            addCriterion("attribute4 >=", value, "attribute4");
            return (Criteria) this;
        }

        public Criteria andAttribute4LessThan(String value) {
            addCriterion("attribute4 <", value, "attribute4");
            return (Criteria) this;
        }

        public Criteria andAttribute4LessThanOrEqualTo(String value) {
            addCriterion("attribute4 <=", value, "attribute4");
            return (Criteria) this;
        }

        public Criteria andAttribute4Like(String value) {
            addCriterion("attribute4 like", value, "attribute4");
            return (Criteria) this;
        }

        public Criteria andAttribute4NotLike(String value) {
            addCriterion("attribute4 not like", value, "attribute4");
            return (Criteria) this;
        }

        public Criteria andAttribute4In(List<String> values) {
            addCriterion("attribute4 in", values, "attribute4");
            return (Criteria) this;
        }

        public Criteria andAttribute4NotIn(List<String> values) {
            addCriterion("attribute4 not in", values, "attribute4");
            return (Criteria) this;
        }

        public Criteria andAttribute4Between(String value1, String value2) {
            addCriterion("attribute4 between", value1, value2, "attribute4");
            return (Criteria) this;
        }

        public Criteria andAttribute4NotBetween(String value1, String value2) {
            addCriterion("attribute4 not between", value1, value2, "attribute4");
            return (Criteria) this;
        }

        public Criteria andAttribute5IsNull() {
            addCriterion("attribute5 is null");
            return (Criteria) this;
        }

        public Criteria andAttribute5IsNotNull() {
            addCriterion("attribute5 is not null");
            return (Criteria) this;
        }

        public Criteria andAttribute5EqualTo(String value) {
            addCriterion("attribute5 =", value, "attribute5");
            return (Criteria) this;
        }

        public Criteria andAttribute5NotEqualTo(String value) {
            addCriterion("attribute5 <>", value, "attribute5");
            return (Criteria) this;
        }

        public Criteria andAttribute5GreaterThan(String value) {
            addCriterion("attribute5 >", value, "attribute5");
            return (Criteria) this;
        }

        public Criteria andAttribute5GreaterThanOrEqualTo(String value) {
            addCriterion("attribute5 >=", value, "attribute5");
            return (Criteria) this;
        }

        public Criteria andAttribute5LessThan(String value) {
            addCriterion("attribute5 <", value, "attribute5");
            return (Criteria) this;
        }

        public Criteria andAttribute5LessThanOrEqualTo(String value) {
            addCriterion("attribute5 <=", value, "attribute5");
            return (Criteria) this;
        }

        public Criteria andAttribute5Like(String value) {
            addCriterion("attribute5 like", value, "attribute5");
            return (Criteria) this;
        }

        public Criteria andAttribute5NotLike(String value) {
            addCriterion("attribute5 not like", value, "attribute5");
            return (Criteria) this;
        }

        public Criteria andAttribute5In(List<String> values) {
            addCriterion("attribute5 in", values, "attribute5");
            return (Criteria) this;
        }

        public Criteria andAttribute5NotIn(List<String> values) {
            addCriterion("attribute5 not in", values, "attribute5");
            return (Criteria) this;
        }

        public Criteria andAttribute5Between(String value1, String value2) {
            addCriterion("attribute5 between", value1, value2, "attribute5");
            return (Criteria) this;
        }

        public Criteria andAttribute5NotBetween(String value1, String value2) {
            addCriterion("attribute5 not between", value1, value2, "attribute5");
            return (Criteria) this;
        }

        public Criteria andDocCreatorIdIsNull() {
            addCriterion("doc_creator_id is null");
            return (Criteria) this;
        }

        public Criteria andDocCreatorIdIsNotNull() {
            addCriterion("doc_creator_id is not null");
            return (Criteria) this;
        }

        public Criteria andDocCreatorIdEqualTo(String value) {
            addCriterion("doc_creator_id =", value, "docCreatorId");
            return (Criteria) this;
        }

        public Criteria andDocCreatorIdNotEqualTo(String value) {
            addCriterion("doc_creator_id <>", value, "docCreatorId");
            return (Criteria) this;
        }

        public Criteria andDocCreatorIdGreaterThan(String value) {
            addCriterion("doc_creator_id >", value, "docCreatorId");
            return (Criteria) this;
        }

        public Criteria andDocCreatorIdGreaterThanOrEqualTo(String value) {
            addCriterion("doc_creator_id >=", value, "docCreatorId");
            return (Criteria) this;
        }

        public Criteria andDocCreatorIdLessThan(String value) {
            addCriterion("doc_creator_id <", value, "docCreatorId");
            return (Criteria) this;
        }

        public Criteria andDocCreatorIdLessThanOrEqualTo(String value) {
            addCriterion("doc_creator_id <=", value, "docCreatorId");
            return (Criteria) this;
        }

        public Criteria andDocCreatorIdLike(String value) {
            addCriterion("doc_creator_id like", value, "docCreatorId");
            return (Criteria) this;
        }

        public Criteria andDocCreatorIdNotLike(String value) {
            addCriterion("doc_creator_id not like", value, "docCreatorId");
            return (Criteria) this;
        }

        public Criteria andDocCreatorIdIn(List<String> values) {
            addCriterion("doc_creator_id in", values, "docCreatorId");
            return (Criteria) this;
        }

        public Criteria andDocCreatorIdNotIn(List<String> values) {
            addCriterion("doc_creator_id not in", values, "docCreatorId");
            return (Criteria) this;
        }

        public Criteria andDocCreatorIdBetween(String value1, String value2) {
            addCriterion("doc_creator_id between", value1, value2, "docCreatorId");
            return (Criteria) this;
        }

        public Criteria andDocCreatorIdNotBetween(String value1, String value2) {
            addCriterion("doc_creator_id not between", value1, value2, "docCreatorId");
            return (Criteria) this;
        }

        public Criteria andDocCreateTimeIsNull() {
            addCriterion("doc_create_time is null");
            return (Criteria) this;
        }

        public Criteria andDocCreateTimeIsNotNull() {
            addCriterion("doc_create_time is not null");
            return (Criteria) this;
        }

        public Criteria andDocCreateTimeEqualTo(Date value) {
            addCriterion("doc_create_time =", value, "docCreateTime");
            return (Criteria) this;
        }

        public Criteria andDocCreateTimeNotEqualTo(Date value) {
            addCriterion("doc_create_time <>", value, "docCreateTime");
            return (Criteria) this;
        }

        public Criteria andDocCreateTimeGreaterThan(Date value) {
            addCriterion("doc_create_time >", value, "docCreateTime");
            return (Criteria) this;
        }

        public Criteria andDocCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("doc_create_time >=", value, "docCreateTime");
            return (Criteria) this;
        }

        public Criteria andDocCreateTimeLessThan(Date value) {
            addCriterion("doc_create_time <", value, "docCreateTime");
            return (Criteria) this;
        }

        public Criteria andDocCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("doc_create_time <=", value, "docCreateTime");
            return (Criteria) this;
        }

        public Criteria andDocCreateTimeIn(List<Date> values) {
            addCriterion("doc_create_time in", values, "docCreateTime");
            return (Criteria) this;
        }

        public Criteria andDocCreateTimeNotIn(List<Date> values) {
            addCriterion("doc_create_time not in", values, "docCreateTime");
            return (Criteria) this;
        }

        public Criteria andDocCreateTimeBetween(Date value1, Date value2) {
            addCriterion("doc_create_time between", value1, value2, "docCreateTime");
            return (Criteria) this;
        }

        public Criteria andDocCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("doc_create_time not between", value1, value2, "docCreateTime");
            return (Criteria) this;
        }

        public Criteria andDocLastUpdateIdIsNull() {
            addCriterion("doc_last_update_id is null");
            return (Criteria) this;
        }

        public Criteria andDocLastUpdateIdIsNotNull() {
            addCriterion("doc_last_update_id is not null");
            return (Criteria) this;
        }

        public Criteria andDocLastUpdateIdEqualTo(String value) {
            addCriterion("doc_last_update_id =", value, "docLastUpdateId");
            return (Criteria) this;
        }

        public Criteria andDocLastUpdateIdNotEqualTo(String value) {
            addCriterion("doc_last_update_id <>", value, "docLastUpdateId");
            return (Criteria) this;
        }

        public Criteria andDocLastUpdateIdGreaterThan(String value) {
            addCriterion("doc_last_update_id >", value, "docLastUpdateId");
            return (Criteria) this;
        }

        public Criteria andDocLastUpdateIdGreaterThanOrEqualTo(String value) {
            addCriterion("doc_last_update_id >=", value, "docLastUpdateId");
            return (Criteria) this;
        }

        public Criteria andDocLastUpdateIdLessThan(String value) {
            addCriterion("doc_last_update_id <", value, "docLastUpdateId");
            return (Criteria) this;
        }

        public Criteria andDocLastUpdateIdLessThanOrEqualTo(String value) {
            addCriterion("doc_last_update_id <=", value, "docLastUpdateId");
            return (Criteria) this;
        }

        public Criteria andDocLastUpdateIdLike(String value) {
            addCriterion("doc_last_update_id like", value, "docLastUpdateId");
            return (Criteria) this;
        }

        public Criteria andDocLastUpdateIdNotLike(String value) {
            addCriterion("doc_last_update_id not like", value, "docLastUpdateId");
            return (Criteria) this;
        }

        public Criteria andDocLastUpdateIdIn(List<String> values) {
            addCriterion("doc_last_update_id in", values, "docLastUpdateId");
            return (Criteria) this;
        }

        public Criteria andDocLastUpdateIdNotIn(List<String> values) {
            addCriterion("doc_last_update_id not in", values, "docLastUpdateId");
            return (Criteria) this;
        }

        public Criteria andDocLastUpdateIdBetween(String value1, String value2) {
            addCriterion("doc_last_update_id between", value1, value2, "docLastUpdateId");
            return (Criteria) this;
        }

        public Criteria andDocLastUpdateIdNotBetween(String value1, String value2) {
            addCriterion("doc_last_update_id not between", value1, value2, "docLastUpdateId");
            return (Criteria) this;
        }

        public Criteria andDocLastUpdateTimeIsNull() {
            addCriterion("doc_last_update_time is null");
            return (Criteria) this;
        }

        public Criteria andDocLastUpdateTimeIsNotNull() {
            addCriterion("doc_last_update_time is not null");
            return (Criteria) this;
        }

        public Criteria andDocLastUpdateTimeEqualTo(Date value) {
            addCriterion("doc_last_update_time =", value, "docLastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andDocLastUpdateTimeNotEqualTo(Date value) {
            addCriterion("doc_last_update_time <>", value, "docLastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andDocLastUpdateTimeGreaterThan(Date value) {
            addCriterion("doc_last_update_time >", value, "docLastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andDocLastUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("doc_last_update_time >=", value, "docLastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andDocLastUpdateTimeLessThan(Date value) {
            addCriterion("doc_last_update_time <", value, "docLastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andDocLastUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("doc_last_update_time <=", value, "docLastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andDocLastUpdateTimeIn(List<Date> values) {
            addCriterion("doc_last_update_time in", values, "docLastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andDocLastUpdateTimeNotIn(List<Date> values) {
            addCriterion("doc_last_update_time not in", values, "docLastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andDocLastUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("doc_last_update_time between", value1, value2, "docLastUpdateTime");
            return (Criteria) this;
        }

        public Criteria andDocLastUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("doc_last_update_time not between", value1, value2, "docLastUpdateTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}