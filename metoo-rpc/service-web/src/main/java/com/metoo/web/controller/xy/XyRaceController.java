package com.metoo.web.controller.xy;


import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 国度，族表 前端控制器
 * </p>
 *
 * @author loongya
 * @since 2020-12-28
 */
@RestController
@RequestMapping("/xy/xy-race")
public class XyRaceController {

    @ApiOperation("获取族")
    @GetMapping("/getRace")
    public List<ReturnRaceDTO> getRace(@RequestHeader("UID")Integer uid){
        List<Race> races = raceDao.findAll();
        List<ReturnRaceDTO> returnRaceDTOS = new ArrayList<>();
        int dw = userInfoDao.findByUid(uid).getDw();
        for (Race race : races){
            ReturnRaceDTO returnRaceDTO = new ReturnRaceDTO();
            returnRaceDTO.setRace(race);
            if (dw>1){
                returnRaceDTO.setState(1);
            }
            returnRaceDTO.setUserName(userInfoDao.findByUid(myRoomDao.findByMyRoomIdAndIsHost(race.getRaceId()).getUid()).getName());
            returnRaceDTOS.add(returnRaceDTO);
        }
        if(dw<4){
            returnRaceDTOS.get(0).setState(0);
            returnRaceDTOS.get(0).setStatusBar(2);
        }
        returnRaceDTOS.get(13).setState(1);
        return returnRaceDTOS;
    }

}
