package com.vip.saturn.job.console.controller.gui;

import com.vip.saturn.job.console.aop.annotation.Audit;
import com.vip.saturn.job.console.aop.annotation.AuditParam;
import com.vip.saturn.job.console.controller.SuccessResponseEntity;
import com.vip.saturn.job.console.domain.RequestResult;
import com.vip.saturn.job.console.exception.SaturnJobConsoleException;
import com.vip.saturn.job.console.service.AlarmStatisticsService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * 所有集群的告警统计
 *
 * @author hebelala
 */
@Controller
@RequestMapping("/console/alarmStatistics")
public class OverallAlarmStatisticsController extends AbstractGUIController {

	@Resource
	private AlarmStatisticsService alarmStatisticsService;

	@ApiResponses(value = {@ApiResponse(code = 200, message = "Success/Fail", response = RequestResult.class)})
	@GetMapping(value = "/abnormalJobs")
	public SuccessResponseEntity getAbnormalJobs(@RequestParam(required = false) String zkClusterKey)
			throws SaturnJobConsoleException {
		if (StringUtils.isBlank(zkClusterKey)) {
			return new SuccessResponseEntity(alarmStatisticsService.getAbnormalJobs());
		}
		return new SuccessResponseEntity(alarmStatisticsService.getAbnormalJobs(zkClusterKey));
	}

	@ApiResponses(value = {@ApiResponse(code = 200, message = "Success/Fail", response = RequestResult.class)})
	@GetMapping(value = "/unableFailoverJobs")
	public SuccessResponseEntity getUnableFailoverJobs(@RequestParam(required = false) String zkClusterKey)
			throws SaturnJobConsoleException {
		if (StringUtils.isBlank(zkClusterKey)) {
			return new SuccessResponseEntity(alarmStatisticsService.getUnableFailoverJobs());
		}
		return new SuccessResponseEntity(alarmStatisticsService.getUnableFailoverJobs(zkClusterKey));
	}

	@ApiResponses(value = {@ApiResponse(code = 200, message = "Success/Fail", response = RequestResult.class)})
	@GetMapping(value = "/timeout4AlarmJobs")
	public SuccessResponseEntity getTimeout4AlarmJobs(@RequestParam(required = false) String zkClusterKey)
			throws SaturnJobConsoleException {
		if (StringUtils.isBlank(zkClusterKey)) {
			return new SuccessResponseEntity(alarmStatisticsService.getTimeout4AlarmJobs());
		}
		return new SuccessResponseEntity(alarmStatisticsService.getTimeout4AlarmJobs(zkClusterKey));
	}

	@ApiResponses(value = {@ApiResponse(code = 200, message = "Success/Fail", response = RequestResult.class)})
	@GetMapping(value = "/abnormalContainers")
	public SuccessResponseEntity getAbnormalContainers(@RequestParam(required = false) String zkClusterKey)
			throws SaturnJobConsoleException {
		if (StringUtils.isBlank(zkClusterKey)) {
			return new SuccessResponseEntity(alarmStatisticsService.getAbnormalContainers());
		}
		return new SuccessResponseEntity(alarmStatisticsService.getAbnormalContainers(zkClusterKey));
	}

	@ApiResponses(value = {@ApiResponse(code = 200, message = "Success/Fail", response = RequestResult.class)})
	@Audit
	@PostMapping(value = "/setAbnormalJobMonitorStatusToRead")
	public SuccessResponseEntity setAbnormalJobMonitorStatusToRead(@AuditParam("uuid") @RequestParam String uuid)
			throws SaturnJobConsoleException {
		alarmStatisticsService.setAbnormalJobMonitorStatusToRead(uuid);
		return new SuccessResponseEntity();
	}

	@ApiResponses(value = {@ApiResponse(code = 200, message = "Success/Fail", response = RequestResult.class)})
	@Audit
	@PostMapping(value = "/setTimeout4AlarmJobMonitorStatusToRead")
	public SuccessResponseEntity setTimeout4AlarmJobMonitorStatusToRead(@AuditParam("uuid") @RequestParam String uuid)
			throws SaturnJobConsoleException {
		alarmStatisticsService.setTimeout4AlarmJobMonitorStatusToRead(uuid);
		return new SuccessResponseEntity();
	}

}
