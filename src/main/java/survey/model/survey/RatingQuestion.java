package survey.model.survey;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Class description.
 * 
 * @author Kevin Ngo.
 *
 */

@Entity
@DiscriminatorValue("RATING")
public class RatingQuestion extends Question {

	/**
	 * Default serialVersionUID.
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name = "rating_scale", nullable = false, columnDefinition = "int default 0")
	private int ratingScale;

	public int getRatingScale() {

		return ratingScale;
	}

	public void setRatingScale(int ratingScale) {

		this.ratingScale = ratingScale;
	}

}
