package com.example.busreservation.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface NodeHeadToMapper {

    public boolean insertOrUpdateNodeHeadTo(@Param("nodeno") String nodeno,
                                            @Param("headto") String headto);
}
