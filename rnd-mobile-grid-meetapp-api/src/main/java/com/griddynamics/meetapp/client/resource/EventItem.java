
package com.griddynamics.meetapp.client.resource;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Builder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "slug",
    "active",
    "title",
    "subject",
    "startDate",
    "city",
    "description",
    "type",
    "endDate"
})
public class EventItem {

    private String slug;
    private Boolean active;
    private String title;
    private Subject subject;
    private Date startDate;
    private City city;
    private String description;
    private String type;
    private String endDate;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public EventItem() {
    }

    /**
     * 
     * @param city
     * @param endDate
     * @param subject
     * @param active
     * @param description
     * @param title
     * @param type
     * @param slug
     * @param startDate
     */
    public EventItem(String slug, Boolean active, String title, Subject subject, Date startDate, City city, String description, String type, String endDate) {
        super();
        this.slug = slug;
        this.active = active;
        this.title = title;
        this.subject = subject;
        this.startDate = startDate;
        this.city = city;
        this.description = description;
        this.type = type;
        this.endDate = endDate;
    }

    public String getType() {
        return type;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
