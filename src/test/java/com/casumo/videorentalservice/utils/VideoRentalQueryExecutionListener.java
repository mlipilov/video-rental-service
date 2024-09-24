package com.casumo.videorentalservice.utils;

import java.util.List;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import net.ttddyy.dsproxy.ExecutionInfo;
import net.ttddyy.dsproxy.QueryInfo;
import net.ttddyy.dsproxy.listener.QueryExecutionListener;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class VideoRentalQueryExecutionListener implements QueryExecutionListener {


  private QueryStats queryStats;
  private boolean counterStarted = false;
  public int getSelectCount() {
    counterStarted = false;
    return queryStats.selectCount;
  }

  public int getInsertCount() {
    counterStarted = false;
    return queryStats.insertCount;
  }

  public int getUpdateCount() {
    counterStarted = false;
    return queryStats.updateCount;
  }

  public int getDeleteCount() {
    counterStarted = false;
    return queryStats.deleteCount;
  }

  @Data
  private static class QueryStats {

    private int selectCount;
    private int insertCount;
    private int updateCount;
    private int deleteCount;
  }

  public void initCounter() {
    queryStats = new QueryStats();
    counterStarted = true;
  }

  @Override
  public void beforeQuery(final ExecutionInfo executionInfo, final List<QueryInfo> list) {
    if (!counterStarted) {
      return;
    }
    final String query = list.stream()
        .map(QueryInfo::getQuery)
        .findFirst()
        .orElse(StringUtils.EMPTY);
    final String statement = StringUtils.substringBefore(query, " ");
    switch (statement) {
      case "select" -> queryStats.selectCount++;
      case "insert" -> queryStats.insertCount++;
      case "update" -> queryStats.updateCount++;
      case "delete" -> queryStats.deleteCount++;
    }
  }

  @Override
  public void afterQuery(final ExecutionInfo executionInfo, final List<QueryInfo> list) {

  }
}
