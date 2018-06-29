package com.rfb.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the RfbLocation entity.
 */
public class RfbLocationDTO implements Serializable {

    private Long id;

    private String locationName;

    private String runDaysOfWeek;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getRunDaysOfWeek() {
        return runDaysOfWeek;
    }

    public void setRunDaysOfWeek(String runDaysOfWeek) {
        this.runDaysOfWeek = runDaysOfWeek;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        RfbLocationDTO rfbLocationDTO = (RfbLocationDTO) o;
        if (rfbLocationDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), rfbLocationDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RfbLocationDTO{" +
            "id=" + getId() +
            ", locationName='" + getLocationName() + "'" +
            ", runDaysOfWeek='" + getRunDaysOfWeek() + "'" +
            "}";
    }
}
