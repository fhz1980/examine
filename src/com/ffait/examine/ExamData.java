package com.ffait.examine;

public class ExamData {
	private String totalNumber;

	private String totalSecond;

	private String examName;

	private int choiceSize;

	private Long examId;
	
	private QuestionPlus question;


	@Override
	public String toString() {
		return "ExamData [totalNumber=" + totalNumber + ", totalSecond=" + totalSecond + ", examName=" + examName
				+ ", choiceSize=" + choiceSize + ", examId=" + examId + ", question=" + question + "]";
	}

	public QuestionPlus getQuestion() {
		return question;
	}

	public void setQuestion(QuestionPlus question) {
		this.question = question;
	}

	public String getTotalNumber() {
		return totalNumber;
	}

	public void setTotalNumber(String totalNumber) {
		this.totalNumber = totalNumber;
	}

	public String getTotalSecond() {
		return totalSecond;
	}

	public void setTotalSecond(String totalSecond) {
		this.totalSecond = totalSecond;
	}

	public String getExamName() {
		return examName;
	}

	public void setExamName(String examName) {
		this.examName = examName;
	}

	public int getChoiceSize() {
		return choiceSize;
	}

	public void setChoiceSize(int choiceSize) {
		this.choiceSize = choiceSize;
	}

	public Long getExamId() {
		return examId;
	}

	public void setExamId(Long examId) {
		this.examId = examId;
	}

	public class QuestionPlus {
		private int questionId;

		private String subject;

		private String options;

		private String answer;

		private int type;

		private int categoryId;

		private int degreeDifficulty;

		private int createId;

		private String createTime;

		private String categoryName;

		private String questionSort;

		private String optionList;

		public int getQuestionId() {
			return questionId;
		}

		public void setQuestionId(int questionId) {
			this.questionId = questionId;
		}

		public String getSubject() {
			return subject;
		}

		public void setSubject(String subject) {
			this.subject = subject;
		}

		public String getOptions() {
			return options;
		}

		public void setOptions(String options) {
			this.options = options;
		}

		public String getAnswer() {
			return answer;
		}

		public void setAnswer(String answer) {
			this.answer = answer;
		}

		public int getType() {
			return type;
		}

		public void setType(int type) {
			this.type = type;
		}

		public int getCategoryId() {
			return categoryId;
		}

		public void setCategoryId(int categoryId) {
			this.categoryId = categoryId;
		}

		public int getDegreeDifficulty() {
			return degreeDifficulty;
		}

		public void setDegreeDifficulty(int degreeDifficulty) {
			this.degreeDifficulty = degreeDifficulty;
		}

		public int getCreateId() {
			return createId;
		}

		public void setCreateId(int createId) {
			this.createId = createId;
		}

		public String getCreateTime() {
			return createTime;
		}

		public void setCreateTime(String createTime) {
			this.createTime = createTime;
		}

		public String getCategoryName() {
			return categoryName;
		}

		public void setCategoryName(String categoryName) {
			this.categoryName = categoryName;
		}

		public String getQuestionSort() {
			return questionSort;
		}

		public void setQuestionSort(String questionSort) {
			this.questionSort = questionSort;
		}

		public String getOptionList() {
			return optionList;
		}

		public void setOptionList(String optionList) {
			this.optionList = optionList;
		}

		@Override
		public String toString() {
			return "Question [questionId=" + questionId + ", subject=" + subject + ", options=" + options + ", answer="
					+ answer + ", type=" + type + ", categoryId=" + categoryId + ", degreeDifficulty="
					+ degreeDifficulty + ", createId=" + createId + ", createTime=" + createTime + ", categoryName="
					+ categoryName + ", questionSort=" + questionSort + ", optionList=" + optionList + "]";
		}
	}
}
