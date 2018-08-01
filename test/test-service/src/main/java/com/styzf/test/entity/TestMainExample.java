package com.styzf.test.entity;

import java.util.ArrayList;
import java.util.List;

public class TestMainExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public TestMainExample() {
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

        public Criteria andTestIdEqualTo(Integer value) {
            addCriterion("test_id =", value, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdNotEqualTo(Integer value) {
            addCriterion("test_id <>", value, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdGreaterThan(Integer value) {
            addCriterion("test_id >", value, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("test_id >=", value, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdLessThan(Integer value) {
            addCriterion("test_id <", value, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdLessThanOrEqualTo(Integer value) {
            addCriterion("test_id <=", value, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdIn(List<Integer> values) {
            addCriterion("test_id in", values, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdNotIn(List<Integer> values) {
            addCriterion("test_id not in", values, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdBetween(Integer value1, Integer value2) {
            addCriterion("test_id between", value1, value2, "testId");
            return (Criteria) this;
        }

        public Criteria andTestIdNotBetween(Integer value1, Integer value2) {
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