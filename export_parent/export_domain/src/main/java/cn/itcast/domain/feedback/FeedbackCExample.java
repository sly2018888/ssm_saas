package cn.itcast.domain.feedback;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FeedbackCExample implements Serializable {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public FeedbackCExample() {
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

    protected abstract static class GeneratedCriteria implements Serializable {
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

        public Criteria andFeedbackIdIsNull() {
            addCriterion("FEEDBACK_ID is null");
            return (Criteria) this;
        }

        public Criteria andFeedbackIdIsNotNull() {
            addCriterion("FEEDBACK_ID is not null");
            return (Criteria) this;
        }

        public Criteria andFeedbackIdEqualTo(String value) {
            addCriterion("FEEDBACK_ID =", value, "feedbackId");
            return (Criteria) this;
        }

        public Criteria andFeedbackIdNotEqualTo(String value) {
            addCriterion("FEEDBACK_ID <>", value, "feedbackId");
            return (Criteria) this;
        }

        public Criteria andFeedbackIdGreaterThan(String value) {
            addCriterion("FEEDBACK_ID >", value, "feedbackId");
            return (Criteria) this;
        }

        public Criteria andFeedbackIdGreaterThanOrEqualTo(String value) {
            addCriterion("FEEDBACK_ID >=", value, "feedbackId");
            return (Criteria) this;
        }

        public Criteria andFeedbackIdLessThan(String value) {
            addCriterion("FEEDBACK_ID <", value, "feedbackId");
            return (Criteria) this;
        }

        public Criteria andFeedbackIdLessThanOrEqualTo(String value) {
            addCriterion("FEEDBACK_ID <=", value, "feedbackId");
            return (Criteria) this;
        }

        public Criteria andFeedbackIdLike(String value) {
            addCriterion("FEEDBACK_ID like", value, "feedbackId");
            return (Criteria) this;
        }

        public Criteria andFeedbackIdNotLike(String value) {
            addCriterion("FEEDBACK_ID not like", value, "feedbackId");
            return (Criteria) this;
        }

        public Criteria andFeedbackIdIn(List<String> values) {
            addCriterion("FEEDBACK_ID in", values, "feedbackId");
            return (Criteria) this;
        }

        public Criteria andFeedbackIdNotIn(List<String> values) {
            addCriterion("FEEDBACK_ID not in", values, "feedbackId");
            return (Criteria) this;
        }

        public Criteria andFeedbackIdBetween(String value1, String value2) {
            addCriterion("FEEDBACK_ID between", value1, value2, "feedbackId");
            return (Criteria) this;
        }

        public Criteria andFeedbackIdNotBetween(String value1, String value2) {
            addCriterion("FEEDBACK_ID not between", value1, value2, "feedbackId");
            return (Criteria) this;
        }

        public Criteria andInputByIsNull() {
            addCriterion("INPUT_BY is null");
            return (Criteria) this;
        }

        public Criteria andInputByIsNotNull() {
            addCriterion("INPUT_BY is not null");
            return (Criteria) this;
        }

        public Criteria andInputByEqualTo(String value) {
            addCriterion("INPUT_BY =", value, "inputBy");
            return (Criteria) this;
        }

        public Criteria andInputByNotEqualTo(String value) {
            addCriterion("INPUT_BY <>", value, "inputBy");
            return (Criteria) this;
        }

        public Criteria andInputByGreaterThan(String value) {
            addCriterion("INPUT_BY >", value, "inputBy");
            return (Criteria) this;
        }

        public Criteria andInputByGreaterThanOrEqualTo(String value) {
            addCriterion("INPUT_BY >=", value, "inputBy");
            return (Criteria) this;
        }

        public Criteria andInputByLessThan(String value) {
            addCriterion("INPUT_BY <", value, "inputBy");
            return (Criteria) this;
        }

        public Criteria andInputByLessThanOrEqualTo(String value) {
            addCriterion("INPUT_BY <=", value, "inputBy");
            return (Criteria) this;
        }

        public Criteria andInputByLike(String value) {
            addCriterion("INPUT_BY like", value, "inputBy");
            return (Criteria) this;
        }

        public Criteria andInputByNotLike(String value) {
            addCriterion("INPUT_BY not like", value, "inputBy");
            return (Criteria) this;
        }

        public Criteria andInputByIn(List<String> values) {
            addCriterion("INPUT_BY in", values, "inputBy");
            return (Criteria) this;
        }

        public Criteria andInputByNotIn(List<String> values) {
            addCriterion("INPUT_BY not in", values, "inputBy");
            return (Criteria) this;
        }

        public Criteria andInputByBetween(String value1, String value2) {
            addCriterion("INPUT_BY between", value1, value2, "inputBy");
            return (Criteria) this;
        }

        public Criteria andInputByNotBetween(String value1, String value2) {
            addCriterion("INPUT_BY not between", value1, value2, "inputBy");
            return (Criteria) this;
        }

        public Criteria andInputTimeIsNull() {
            addCriterion("INPUT_TIME is null");
            return (Criteria) this;
        }

        public Criteria andInputTimeIsNotNull() {
            addCriterion("INPUT_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andInputTimeEqualTo(Date value) {
            addCriterion("INPUT_TIME =", value, "inputTime");
            return (Criteria) this;
        }

        public Criteria andInputTimeNotEqualTo(Date value) {
            addCriterion("INPUT_TIME <>", value, "inputTime");
            return (Criteria) this;
        }

        public Criteria andInputTimeGreaterThan(Date value) {
            addCriterion("INPUT_TIME >", value, "inputTime");
            return (Criteria) this;
        }

        public Criteria andInputTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("INPUT_TIME >=", value, "inputTime");
            return (Criteria) this;
        }

        public Criteria andInputTimeLessThan(Date value) {
            addCriterion("INPUT_TIME <", value, "inputTime");
            return (Criteria) this;
        }

        public Criteria andInputTimeLessThanOrEqualTo(Date value) {
            addCriterion("INPUT_TIME <=", value, "inputTime");
            return (Criteria) this;
        }

        public Criteria andInputTimeIn(List<Date> values) {
            addCriterion("INPUT_TIME in", values, "inputTime");
            return (Criteria) this;
        }

        public Criteria andInputTimeNotIn(List<Date> values) {
            addCriterion("INPUT_TIME not in", values, "inputTime");
            return (Criteria) this;
        }

        public Criteria andInputTimeBetween(Date value1, Date value2) {
            addCriterion("INPUT_TIME between", value1, value2, "inputTime");
            return (Criteria) this;
        }

        public Criteria andInputTimeNotBetween(Date value1, Date value2) {
            addCriterion("INPUT_TIME not between", value1, value2, "inputTime");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("TITLE is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("TITLE is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("TITLE =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("TITLE <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("TITLE >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("TITLE >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("TITLE <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("TITLE <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("TITLE like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("TITLE not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("TITLE in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("TITLE not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("TITLE between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("TITLE not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andContentIsNull() {
            addCriterion("CONTENT is null");
            return (Criteria) this;
        }

        public Criteria andContentIsNotNull() {
            addCriterion("CONTENT is not null");
            return (Criteria) this;
        }

        public Criteria andContentEqualTo(String value) {
            addCriterion("CONTENT =", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotEqualTo(String value) {
            addCriterion("CONTENT <>", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThan(String value) {
            addCriterion("CONTENT >", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentGreaterThanOrEqualTo(String value) {
            addCriterion("CONTENT >=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThan(String value) {
            addCriterion("CONTENT <", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLessThanOrEqualTo(String value) {
            addCriterion("CONTENT <=", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentLike(String value) {
            addCriterion("CONTENT like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotLike(String value) {
            addCriterion("CONTENT not like", value, "content");
            return (Criteria) this;
        }

        public Criteria andContentIn(List<String> values) {
            addCriterion("CONTENT in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotIn(List<String> values) {
            addCriterion("CONTENT not in", values, "content");
            return (Criteria) this;
        }

        public Criteria andContentBetween(String value1, String value2) {
            addCriterion("CONTENT between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andContentNotBetween(String value1, String value2) {
            addCriterion("CONTENT not between", value1, value2, "content");
            return (Criteria) this;
        }

        public Criteria andClassTypeIsNull() {
            addCriterion("CLASS_TYPE is null");
            return (Criteria) this;
        }

        public Criteria andClassTypeIsNotNull() {
            addCriterion("CLASS_TYPE is not null");
            return (Criteria) this;
        }

        public Criteria andClassTypeEqualTo(String value) {
            addCriterion("CLASS_TYPE =", value, "classType");
            return (Criteria) this;
        }

        public Criteria andClassTypeNotEqualTo(String value) {
            addCriterion("CLASS_TYPE <>", value, "classType");
            return (Criteria) this;
        }

        public Criteria andClassTypeGreaterThan(String value) {
            addCriterion("CLASS_TYPE >", value, "classType");
            return (Criteria) this;
        }

        public Criteria andClassTypeGreaterThanOrEqualTo(String value) {
            addCriterion("CLASS_TYPE >=", value, "classType");
            return (Criteria) this;
        }

        public Criteria andClassTypeLessThan(String value) {
            addCriterion("CLASS_TYPE <", value, "classType");
            return (Criteria) this;
        }

        public Criteria andClassTypeLessThanOrEqualTo(String value) {
            addCriterion("CLASS_TYPE <=", value, "classType");
            return (Criteria) this;
        }

        public Criteria andClassTypeLike(String value) {
            addCriterion("CLASS_TYPE like", value, "classType");
            return (Criteria) this;
        }

        public Criteria andClassTypeNotLike(String value) {
            addCriterion("CLASS_TYPE not like", value, "classType");
            return (Criteria) this;
        }

        public Criteria andClassTypeIn(List<String> values) {
            addCriterion("CLASS_TYPE in", values, "classType");
            return (Criteria) this;
        }

        public Criteria andClassTypeNotIn(List<String> values) {
            addCriterion("CLASS_TYPE not in", values, "classType");
            return (Criteria) this;
        }

        public Criteria andClassTypeBetween(String value1, String value2) {
            addCriterion("CLASS_TYPE between", value1, value2, "classType");
            return (Criteria) this;
        }

        public Criteria andClassTypeNotBetween(String value1, String value2) {
            addCriterion("CLASS_TYPE not between", value1, value2, "classType");
            return (Criteria) this;
        }

        public Criteria andTelIsNull() {
            addCriterion("TEL is null");
            return (Criteria) this;
        }

        public Criteria andTelIsNotNull() {
            addCriterion("TEL is not null");
            return (Criteria) this;
        }

        public Criteria andTelEqualTo(String value) {
            addCriterion("TEL =", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotEqualTo(String value) {
            addCriterion("TEL <>", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelGreaterThan(String value) {
            addCriterion("TEL >", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelGreaterThanOrEqualTo(String value) {
            addCriterion("TEL >=", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLessThan(String value) {
            addCriterion("TEL <", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLessThanOrEqualTo(String value) {
            addCriterion("TEL <=", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelLike(String value) {
            addCriterion("TEL like", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotLike(String value) {
            addCriterion("TEL not like", value, "tel");
            return (Criteria) this;
        }

        public Criteria andTelIn(List<String> values) {
            addCriterion("TEL in", values, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotIn(List<String> values) {
            addCriterion("TEL not in", values, "tel");
            return (Criteria) this;
        }

        public Criteria andTelBetween(String value1, String value2) {
            addCriterion("TEL between", value1, value2, "tel");
            return (Criteria) this;
        }

        public Criteria andTelNotBetween(String value1, String value2) {
            addCriterion("TEL not between", value1, value2, "tel");
            return (Criteria) this;
        }

        public Criteria andAnswerByIsNull() {
            addCriterion("ANSWER_BY is null");
            return (Criteria) this;
        }

        public Criteria andAnswerByIsNotNull() {
            addCriterion("ANSWER_BY is not null");
            return (Criteria) this;
        }

        public Criteria andAnswerByEqualTo(String value) {
            addCriterion("ANSWER_BY =", value, "answerBy");
            return (Criteria) this;
        }

        public Criteria andAnswerByNotEqualTo(String value) {
            addCriterion("ANSWER_BY <>", value, "answerBy");
            return (Criteria) this;
        }

        public Criteria andAnswerByGreaterThan(String value) {
            addCriterion("ANSWER_BY >", value, "answerBy");
            return (Criteria) this;
        }

        public Criteria andAnswerByGreaterThanOrEqualTo(String value) {
            addCriterion("ANSWER_BY >=", value, "answerBy");
            return (Criteria) this;
        }

        public Criteria andAnswerByLessThan(String value) {
            addCriterion("ANSWER_BY <", value, "answerBy");
            return (Criteria) this;
        }

        public Criteria andAnswerByLessThanOrEqualTo(String value) {
            addCriterion("ANSWER_BY <=", value, "answerBy");
            return (Criteria) this;
        }

        public Criteria andAnswerByLike(String value) {
            addCriterion("ANSWER_BY like", value, "answerBy");
            return (Criteria) this;
        }

        public Criteria andAnswerByNotLike(String value) {
            addCriterion("ANSWER_BY not like", value, "answerBy");
            return (Criteria) this;
        }

        public Criteria andAnswerByIn(List<String> values) {
            addCriterion("ANSWER_BY in", values, "answerBy");
            return (Criteria) this;
        }

        public Criteria andAnswerByNotIn(List<String> values) {
            addCriterion("ANSWER_BY not in", values, "answerBy");
            return (Criteria) this;
        }

        public Criteria andAnswerByBetween(String value1, String value2) {
            addCriterion("ANSWER_BY between", value1, value2, "answerBy");
            return (Criteria) this;
        }

        public Criteria andAnswerByNotBetween(String value1, String value2) {
            addCriterion("ANSWER_BY not between", value1, value2, "answerBy");
            return (Criteria) this;
        }

        public Criteria andAnswerTimeIsNull() {
            addCriterion("ANSWER_TIME is null");
            return (Criteria) this;
        }

        public Criteria andAnswerTimeIsNotNull() {
            addCriterion("ANSWER_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andAnswerTimeEqualTo(Date value) {
            addCriterion("ANSWER_TIME =", value, "answerTime");
            return (Criteria) this;
        }

        public Criteria andAnswerTimeNotEqualTo(Date value) {
            addCriterion("ANSWER_TIME <>", value, "answerTime");
            return (Criteria) this;
        }

        public Criteria andAnswerTimeGreaterThan(Date value) {
            addCriterion("ANSWER_TIME >", value, "answerTime");
            return (Criteria) this;
        }

        public Criteria andAnswerTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("ANSWER_TIME >=", value, "answerTime");
            return (Criteria) this;
        }

        public Criteria andAnswerTimeLessThan(Date value) {
            addCriterion("ANSWER_TIME <", value, "answerTime");
            return (Criteria) this;
        }

        public Criteria andAnswerTimeLessThanOrEqualTo(Date value) {
            addCriterion("ANSWER_TIME <=", value, "answerTime");
            return (Criteria) this;
        }

        public Criteria andAnswerTimeIn(List<Date> values) {
            addCriterion("ANSWER_TIME in", values, "answerTime");
            return (Criteria) this;
        }

        public Criteria andAnswerTimeNotIn(List<Date> values) {
            addCriterion("ANSWER_TIME not in", values, "answerTime");
            return (Criteria) this;
        }

        public Criteria andAnswerTimeBetween(Date value1, Date value2) {
            addCriterion("ANSWER_TIME between", value1, value2, "answerTime");
            return (Criteria) this;
        }

        public Criteria andAnswerTimeNotBetween(Date value1, Date value2) {
            addCriterion("ANSWER_TIME not between", value1, value2, "answerTime");
            return (Criteria) this;
        }

        public Criteria andSolveMethodIsNull() {
            addCriterion("SOLVE_METHOD is null");
            return (Criteria) this;
        }

        public Criteria andSolveMethodIsNotNull() {
            addCriterion("SOLVE_METHOD is not null");
            return (Criteria) this;
        }

        public Criteria andSolveMethodEqualTo(String value) {
            addCriterion("SOLVE_METHOD =", value, "solveMethod");
            return (Criteria) this;
        }

        public Criteria andSolveMethodNotEqualTo(String value) {
            addCriterion("SOLVE_METHOD <>", value, "solveMethod");
            return (Criteria) this;
        }

        public Criteria andSolveMethodGreaterThan(String value) {
            addCriterion("SOLVE_METHOD >", value, "solveMethod");
            return (Criteria) this;
        }

        public Criteria andSolveMethodGreaterThanOrEqualTo(String value) {
            addCriterion("SOLVE_METHOD >=", value, "solveMethod");
            return (Criteria) this;
        }

        public Criteria andSolveMethodLessThan(String value) {
            addCriterion("SOLVE_METHOD <", value, "solveMethod");
            return (Criteria) this;
        }

        public Criteria andSolveMethodLessThanOrEqualTo(String value) {
            addCriterion("SOLVE_METHOD <=", value, "solveMethod");
            return (Criteria) this;
        }

        public Criteria andSolveMethodLike(String value) {
            addCriterion("SOLVE_METHOD like", value, "solveMethod");
            return (Criteria) this;
        }

        public Criteria andSolveMethodNotLike(String value) {
            addCriterion("SOLVE_METHOD not like", value, "solveMethod");
            return (Criteria) this;
        }

        public Criteria andSolveMethodIn(List<String> values) {
            addCriterion("SOLVE_METHOD in", values, "solveMethod");
            return (Criteria) this;
        }

        public Criteria andSolveMethodNotIn(List<String> values) {
            addCriterion("SOLVE_METHOD not in", values, "solveMethod");
            return (Criteria) this;
        }

        public Criteria andSolveMethodBetween(String value1, String value2) {
            addCriterion("SOLVE_METHOD between", value1, value2, "solveMethod");
            return (Criteria) this;
        }

        public Criteria andSolveMethodNotBetween(String value1, String value2) {
            addCriterion("SOLVE_METHOD not between", value1, value2, "solveMethod");
            return (Criteria) this;
        }

        public Criteria andResolutionIsNull() {
            addCriterion("RESOLUTION is null");
            return (Criteria) this;
        }

        public Criteria andResolutionIsNotNull() {
            addCriterion("RESOLUTION is not null");
            return (Criteria) this;
        }

        public Criteria andResolutionEqualTo(String value) {
            addCriterion("RESOLUTION =", value, "resolution");
            return (Criteria) this;
        }

        public Criteria andResolutionNotEqualTo(String value) {
            addCriterion("RESOLUTION <>", value, "resolution");
            return (Criteria) this;
        }

        public Criteria andResolutionGreaterThan(String value) {
            addCriterion("RESOLUTION >", value, "resolution");
            return (Criteria) this;
        }

        public Criteria andResolutionGreaterThanOrEqualTo(String value) {
            addCriterion("RESOLUTION >=", value, "resolution");
            return (Criteria) this;
        }

        public Criteria andResolutionLessThan(String value) {
            addCriterion("RESOLUTION <", value, "resolution");
            return (Criteria) this;
        }

        public Criteria andResolutionLessThanOrEqualTo(String value) {
            addCriterion("RESOLUTION <=", value, "resolution");
            return (Criteria) this;
        }

        public Criteria andResolutionLike(String value) {
            addCriterion("RESOLUTION like", value, "resolution");
            return (Criteria) this;
        }

        public Criteria andResolutionNotLike(String value) {
            addCriterion("RESOLUTION not like", value, "resolution");
            return (Criteria) this;
        }

        public Criteria andResolutionIn(List<String> values) {
            addCriterion("RESOLUTION in", values, "resolution");
            return (Criteria) this;
        }

        public Criteria andResolutionNotIn(List<String> values) {
            addCriterion("RESOLUTION not in", values, "resolution");
            return (Criteria) this;
        }

        public Criteria andResolutionBetween(String value1, String value2) {
            addCriterion("RESOLUTION between", value1, value2, "resolution");
            return (Criteria) this;
        }

        public Criteria andResolutionNotBetween(String value1, String value2) {
            addCriterion("RESOLUTION not between", value1, value2, "resolution");
            return (Criteria) this;
        }

        public Criteria andDifficultyIsNull() {
            addCriterion("DIFFICULTY is null");
            return (Criteria) this;
        }

        public Criteria andDifficultyIsNotNull() {
            addCriterion("DIFFICULTY is not null");
            return (Criteria) this;
        }

        public Criteria andDifficultyEqualTo(String value) {
            addCriterion("DIFFICULTY =", value, "difficulty");
            return (Criteria) this;
        }

        public Criteria andDifficultyNotEqualTo(String value) {
            addCriterion("DIFFICULTY <>", value, "difficulty");
            return (Criteria) this;
        }

        public Criteria andDifficultyGreaterThan(String value) {
            addCriterion("DIFFICULTY >", value, "difficulty");
            return (Criteria) this;
        }

        public Criteria andDifficultyGreaterThanOrEqualTo(String value) {
            addCriterion("DIFFICULTY >=", value, "difficulty");
            return (Criteria) this;
        }

        public Criteria andDifficultyLessThan(String value) {
            addCriterion("DIFFICULTY <", value, "difficulty");
            return (Criteria) this;
        }

        public Criteria andDifficultyLessThanOrEqualTo(String value) {
            addCriterion("DIFFICULTY <=", value, "difficulty");
            return (Criteria) this;
        }

        public Criteria andDifficultyLike(String value) {
            addCriterion("DIFFICULTY like", value, "difficulty");
            return (Criteria) this;
        }

        public Criteria andDifficultyNotLike(String value) {
            addCriterion("DIFFICULTY not like", value, "difficulty");
            return (Criteria) this;
        }

        public Criteria andDifficultyIn(List<String> values) {
            addCriterion("DIFFICULTY in", values, "difficulty");
            return (Criteria) this;
        }

        public Criteria andDifficultyNotIn(List<String> values) {
            addCriterion("DIFFICULTY not in", values, "difficulty");
            return (Criteria) this;
        }

        public Criteria andDifficultyBetween(String value1, String value2) {
            addCriterion("DIFFICULTY between", value1, value2, "difficulty");
            return (Criteria) this;
        }

        public Criteria andDifficultyNotBetween(String value1, String value2) {
            addCriterion("DIFFICULTY not between", value1, value2, "difficulty");
            return (Criteria) this;
        }

        public Criteria andIsShareIsNull() {
            addCriterion("IS_SHARE is null");
            return (Criteria) this;
        }

        public Criteria andIsShareIsNotNull() {
            addCriterion("IS_SHARE is not null");
            return (Criteria) this;
        }

        public Criteria andIsShareEqualTo(String value) {
            addCriterion("IS_SHARE =", value, "isShare");
            return (Criteria) this;
        }

        public Criteria andIsShareNotEqualTo(String value) {
            addCriterion("IS_SHARE <>", value, "isShare");
            return (Criteria) this;
        }

        public Criteria andIsShareGreaterThan(String value) {
            addCriterion("IS_SHARE >", value, "isShare");
            return (Criteria) this;
        }

        public Criteria andIsShareGreaterThanOrEqualTo(String value) {
            addCriterion("IS_SHARE >=", value, "isShare");
            return (Criteria) this;
        }

        public Criteria andIsShareLessThan(String value) {
            addCriterion("IS_SHARE <", value, "isShare");
            return (Criteria) this;
        }

        public Criteria andIsShareLessThanOrEqualTo(String value) {
            addCriterion("IS_SHARE <=", value, "isShare");
            return (Criteria) this;
        }

        public Criteria andIsShareLike(String value) {
            addCriterion("IS_SHARE like", value, "isShare");
            return (Criteria) this;
        }

        public Criteria andIsShareNotLike(String value) {
            addCriterion("IS_SHARE not like", value, "isShare");
            return (Criteria) this;
        }

        public Criteria andIsShareIn(List<String> values) {
            addCriterion("IS_SHARE in", values, "isShare");
            return (Criteria) this;
        }

        public Criteria andIsShareNotIn(List<String> values) {
            addCriterion("IS_SHARE not in", values, "isShare");
            return (Criteria) this;
        }

        public Criteria andIsShareBetween(String value1, String value2) {
            addCriterion("IS_SHARE between", value1, value2, "isShare");
            return (Criteria) this;
        }

        public Criteria andIsShareNotBetween(String value1, String value2) {
            addCriterion("IS_SHARE not between", value1, value2, "isShare");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("STATE is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("STATE is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(String value) {
            addCriterion("STATE =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(String value) {
            addCriterion("STATE <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(String value) {
            addCriterion("STATE >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(String value) {
            addCriterion("STATE >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(String value) {
            addCriterion("STATE <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(String value) {
            addCriterion("STATE <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLike(String value) {
            addCriterion("STATE like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotLike(String value) {
            addCriterion("STATE not like", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<String> values) {
            addCriterion("STATE in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<String> values) {
            addCriterion("STATE not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(String value1, String value2) {
            addCriterion("STATE between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(String value1, String value2) {
            addCriterion("STATE not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNull() {
            addCriterion("CREATE_BY is null");
            return (Criteria) this;
        }

        public Criteria andCreateByIsNotNull() {
            addCriterion("CREATE_BY is not null");
            return (Criteria) this;
        }

        public Criteria andCreateByEqualTo(String value) {
            addCriterion("CREATE_BY =", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotEqualTo(String value) {
            addCriterion("CREATE_BY <>", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThan(String value) {
            addCriterion("CREATE_BY >", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByGreaterThanOrEqualTo(String value) {
            addCriterion("CREATE_BY >=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThan(String value) {
            addCriterion("CREATE_BY <", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLessThanOrEqualTo(String value) {
            addCriterion("CREATE_BY <=", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByLike(String value) {
            addCriterion("CREATE_BY like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotLike(String value) {
            addCriterion("CREATE_BY not like", value, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByIn(List<String> values) {
            addCriterion("CREATE_BY in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotIn(List<String> values) {
            addCriterion("CREATE_BY not in", values, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByBetween(String value1, String value2) {
            addCriterion("CREATE_BY between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateByNotBetween(String value1, String value2) {
            addCriterion("CREATE_BY not between", value1, value2, "createBy");
            return (Criteria) this;
        }

        public Criteria andCreateDeptIsNull() {
            addCriterion("CREATE_DEPT is null");
            return (Criteria) this;
        }

        public Criteria andCreateDeptIsNotNull() {
            addCriterion("CREATE_DEPT is not null");
            return (Criteria) this;
        }

        public Criteria andCreateDeptEqualTo(String value) {
            addCriterion("CREATE_DEPT =", value, "createDept");
            return (Criteria) this;
        }

        public Criteria andCreateDeptNotEqualTo(String value) {
            addCriterion("CREATE_DEPT <>", value, "createDept");
            return (Criteria) this;
        }

        public Criteria andCreateDeptGreaterThan(String value) {
            addCriterion("CREATE_DEPT >", value, "createDept");
            return (Criteria) this;
        }

        public Criteria andCreateDeptGreaterThanOrEqualTo(String value) {
            addCriterion("CREATE_DEPT >=", value, "createDept");
            return (Criteria) this;
        }

        public Criteria andCreateDeptLessThan(String value) {
            addCriterion("CREATE_DEPT <", value, "createDept");
            return (Criteria) this;
        }

        public Criteria andCreateDeptLessThanOrEqualTo(String value) {
            addCriterion("CREATE_DEPT <=", value, "createDept");
            return (Criteria) this;
        }

        public Criteria andCreateDeptLike(String value) {
            addCriterion("CREATE_DEPT like", value, "createDept");
            return (Criteria) this;
        }

        public Criteria andCreateDeptNotLike(String value) {
            addCriterion("CREATE_DEPT not like", value, "createDept");
            return (Criteria) this;
        }

        public Criteria andCreateDeptIn(List<String> values) {
            addCriterion("CREATE_DEPT in", values, "createDept");
            return (Criteria) this;
        }

        public Criteria andCreateDeptNotIn(List<String> values) {
            addCriterion("CREATE_DEPT not in", values, "createDept");
            return (Criteria) this;
        }

        public Criteria andCreateDeptBetween(String value1, String value2) {
            addCriterion("CREATE_DEPT between", value1, value2, "createDept");
            return (Criteria) this;
        }

        public Criteria andCreateDeptNotBetween(String value1, String value2) {
            addCriterion("CREATE_DEPT not between", value1, value2, "createDept");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("CREATE_TIME is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("CREATE_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("CREATE_TIME =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("CREATE_TIME <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("CREATE_TIME >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("CREATE_TIME <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("CREATE_TIME <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("CREATE_TIME in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("CREATE_TIME not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("CREATE_TIME not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNull() {
            addCriterion("COMPANY_ID is null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIsNotNull() {
            addCriterion("COMPANY_ID is not null");
            return (Criteria) this;
        }

        public Criteria andCompanyIdEqualTo(String value) {
            addCriterion("COMPANY_ID =", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotEqualTo(String value) {
            addCriterion("COMPANY_ID <>", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThan(String value) {
            addCriterion("COMPANY_ID >", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdGreaterThanOrEqualTo(String value) {
            addCriterion("COMPANY_ID >=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThan(String value) {
            addCriterion("COMPANY_ID <", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLessThanOrEqualTo(String value) {
            addCriterion("COMPANY_ID <=", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdLike(String value) {
            addCriterion("COMPANY_ID like", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotLike(String value) {
            addCriterion("COMPANY_ID not like", value, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdIn(List<String> values) {
            addCriterion("COMPANY_ID in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotIn(List<String> values) {
            addCriterion("COMPANY_ID not in", values, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdBetween(String value1, String value2) {
            addCriterion("COMPANY_ID between", value1, value2, "companyId");
            return (Criteria) this;
        }

        public Criteria andCompanyIdNotBetween(String value1, String value2) {
            addCriterion("COMPANY_ID not between", value1, value2, "companyId");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria implements Serializable {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion  implements Serializable{
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