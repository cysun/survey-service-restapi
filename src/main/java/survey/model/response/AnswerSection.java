package survey.model.response;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;


/**
 * Class description.
 * 
 * @author Kevin Ngo.
 *
 */

@Entity
@Table(name = "answer_section")
@JsonPropertyOrder({"id", "responseId", "description"})
public class AnswerSection implements Serializable {
  /**
   * Default serialVersionUID.
   * 
   */

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue
  private Long id;

  @ManyToOne
  @JsonIgnore
  @Transient
  private SurveyResponse response;

  @Column(name = "response_section_index", nullable = false)
  @JsonIgnore
  private int index;

  @OneToMany(cascade = CascadeType.MERGE)
  @JoinColumn(name = "answer_section_id")
  @OrderColumn(name = "answer_index")
  private List<Answer> answers;
  
  @Transient
  @JsonProperty("description")
  private String description;
  
  public void updateDescription() {
  	this.setDescription(response.getSurvey().getQuestionSections().get(this.index).getDescription());
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public SurveyResponse getResponse() {
    return response;
  }

  public void setResponse(SurveyResponse response) {
    this.response = response;
  }

  public int getIndex() {
    return index;
  }

  public void setIndex(int index) {
    this.index = index;
  }

  public List<Answer> getAnswers() {
    return answers;
  }

  public void setAnswers(List<Answer> answers) {
    this.answers = answers;
  }

	public String getDescription() {

		return description;
	}

	public void setDescription(String description) {

		this.description = description;
	}

//  @JsonIgnore
//	private String getDescription() {
//
//		return description;
//	}
//
//  @JsonIgnore
//	private void setDescription(String description) {
//
//		this.description = description;
//	}
}
