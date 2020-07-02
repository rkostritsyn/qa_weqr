
package com.griddynamics.meetapp.client.resource;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "name",
    "shortName",
    "offshore",
    "country",
    "region",
    "aliases",
    "timezone",
    "imagePath"
})
public class City {

    private String name;
    private String shortName;
    private Boolean offshore;
    private Country country;
    private String region;
    private Object aliases;
    private String timezone;
    private String imagePath;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * No args constructor for use in serialization
     * 
     */
    public City() {
    }

    /**
     * 
     * @param country
     * @param aliases
     * @param timezone
     * @param imagePath
     * @param name
     * @param shortName
     * @param region
     * @param offshore
     */
    public City(String name, String shortName, Boolean offshore, Country country, String region, Object aliases, String timezone, String imagePath) {
        super();
        this.name = name;
        this.shortName = shortName;
        this.offshore = offshore;
        this.country = country;
        this.region = region;
        this.aliases = aliases;
        this.timezone = timezone;
        this.imagePath = imagePath;
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
