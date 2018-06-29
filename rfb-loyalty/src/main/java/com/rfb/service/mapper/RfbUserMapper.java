package com.rfb.service.mapper;

import com.rfb.domain.*;
import com.rfb.service.dto.RfbUserDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity RfbUser and its DTO RfbUserDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface RfbUserMapper extends EntityMapper<RfbUserDTO, RfbUser> {



    default RfbUser fromId(Long id) {
        if (id == null) {
            return null;
        }
        RfbUser rfbUser = new RfbUser();
        rfbUser.setId(id);
        return rfbUser;
    }
}
