package by.iba.common.mapper.core;


import by.iba.common.domain.core.BaseEntity;
import by.iba.common.dto.core.BaseReq;
import by.iba.common.dto.core.BaseResp;

public interface FullObjectMapper<E extends BaseEntity, D extends BaseResp, R extends BaseReq>
        extends SimpleObjectMapper<E, D> {

    E toEntityFromReq(final R r);

}