package edu.gimt.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Road-Level Entity class implementation.
 * @author Victor M. Sobrino - TFG
 */
@Entity
@Table(name = "gimt_road_level")
@NamedQueries({@NamedQuery(name = "RoadLevel.findAll", query = "SELECT rl FROM RoadLevel rl"),
			   @NamedQuery(name = "RoadLevel.findByColour", query = "SELECT rl FROM RoadLevel rl WHERE rl.levelColour = :levelColour")})
public class RoadLevel {

	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id_road_level")
	private Integer idRoadLevel;

	@Column(name = "level_colour")
	private String levelColour;

	@Column(name = "level_flow")
	private String levelFlow;

	/**
	 * @return the levelColour
	 */
	public String getLevelColour() {
		return levelColour;
	}

	/**
	 * @param levelColour the levelColour to set
	 */
	public void setLevelColour(String levelColour) {
		this.levelColour = levelColour;
	}

	/**
	 * @return the levelFlow
	 */
	public String getLevelFlow() {
		return levelFlow;
	}

	/**
	 * @param levelFlow the levelFlow to set
	 */
	public void setLevelFlow(String levelFlow) {
		this.levelFlow = levelFlow;
	}

	/**
	 * @return the idRoadLevel
	 */
	public Integer getIdRoadLevel() {
		return idRoadLevel;
	}

}
