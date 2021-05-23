package com.example.busreservation.mapper;

import com.example.busreservation.dto.Node;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface NodeHeadToMapper {

    public boolean insertOrUpdateNodeHeadTo(@Param("nodeno") String nodeno,
                                            @Param("headto") String headto);
    public List<Node> getAllNodeHeadToMap();
}
