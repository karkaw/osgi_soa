<%@page import="java.lang.management.*" %>
<%@page import="java.util.ArrayList" %>
<%-- <%@page import="com.sina.sae.util.SaeUserInfo"%> --%>
<%@page import="java.util.List" %>
<%@ page language="java" pageEncoding="utf-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"><html><head><meta http-equiv="Content-Type" content="text/html; charset=utf-8" /><title>Sina App Engine Java</title><link rel="shortcut icon" href="<%=request.getContextPath() %>/favicon.ico" /><link rel="stylesheet" type="text/css" href="css/theme.css" /><link rel="stylesheet" type="text/css" href="css/style.css" /><script type="text/javascript" src="js/jquery-1.7.1.js"></script><script src="js/highcharts.js"></script><script src="js/exporting.js"></script><script src="js/chartsutil.js"></script><!--[if IE]><link rel="stylesheet" type="text/css" href="css/ie-sucks.css" /><![endif]--><% RuntimeMXBean rMXBean = ManagementFactory.getRuntimeMXBean();
    ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
    List<GarbageCollectorMXBean> aGCMXBeans = ManagementFactory.getGarbageCollectorMXBeans();
    String[] node = rMXBean.getName().split("@");//节点名称

    String nodename = node[node.length - 1];
    String currentNode = nodename + request.getLocalPort();
    long cputime = threadMXBean.getCurrentThreadCpuTime() / 1000000;//cpu时间
    long livethread = threadMXBean.getThreadCount();//活动线程数
    long topthread = threadMXBean.getPeakThreadCount();//峰值线程数
    long deamonthread = threadMXBean.getDaemonThreadCount();//守护线程数
    long totalthread = threadMXBean.getTotalStartedThreadCount();//创建线程总数
    long[] ids = threadMXBean.getAllThreadIds();//所有线程ID号	//计算各类状态线程数量
    long newcount = 0, runcount = 0, blockedcount = 0, waitingcount = 0, twaitcount = 0, terminatedcount = 0;
    List<ThreadInfo> threads = new ArrayList<ThreadInfo>();
    for (int i = 0; i < ids.length; i++) {
        ThreadInfo tInfo = threadMXBean.getThreadInfo(ids[i], Integer.MAX_VALUE);
        threads.add(tInfo);
        Thread.State state = tInfo.getThreadState();
        if (state.equals(Thread.State.NEW)) {
            newcount++;
        } else if (state.equals(Thread.State.RUNNABLE)) {
            runcount++;
        } else if (state.equals(Thread.State.BLOCKED)) {
            blockedcount++;
        } else if (state.equals(Thread.State.WAITING)) {
            waitingcount++;
        } else if (state.equals(Thread.State.TIMED_WAITING)) {
            twaitcount++;
        } else if (state.equals(Thread.State.TERMINATED)) {
            terminatedcount++;
        }
    }%>
<script type="text/javascript">    $(function () {
    $(".over").hover(function () {
        $(this).css("background-color", "#d9e6f0");
    }, function () {
        $(this).css("background-color", "");
    }).click(function () {
        ajaxData(this.id);
    });
});
function ajaxData(id) {
    $.ajax({type: "post", url: "ajax.jsp", data: {type: "threads", node: "<%=currentNode%>", threadId: id}, success: function (data, textStatus) {
        var text = $.trim(data);
        if (textStatus == "success" && text != "error node") {
            $("#stacktrace").html(text);
        } else {				//alert("Request error node,try again later!"+text);
            ajaxData(id);
        }
    }});
}</script>
</head><body>
<div id="container">
    <div id="header"><%--         	<h2>Sina App Engine Java  -    <%=SaeUserInfo.getAppName()%></h2> --%>
        <div id="topmenu">
            <ul>
                <li><a href="WEB-INF/html/app/index.html">Overview</a></li>
                <li><a href="appinfo.jsp">Application</a></li>
                <li><a href="monitor.jsp">Monitor</a></li>
                <li><a href="memory.jsp">Memory</a></li>
                <li class="current"><a href="threads.jsp">Threads</a></li>
            </ul>
        </div>
    </div>
    <div id="wrapper">
        <div id="bigcontent">
            <div id="rightnow"><h3
                    class="reallynow">                        <%-- <span>Threads  -  <%=SaeUserInfo.getAppName() %></span> --%>
                <a href="threads.jsp"><b>Refresh All</b> </a> <br/></h3>

                <p class="youhave"><strong>节点名称: <%=nodename %>
                </strong> <strong>线程CPU时间: <%=cputime %> ms </strong></p></div>
            <br/>

            <div id="rightnow"><h3 class="reallynow"><span>Overview </span> <br/></h3>

                <p class="youhave"><strong>创建线程总数: <%=totalthread %>
                </strong> <strong> 活动线程数: <%=livethread %>
                </strong> <strong>峰值线程数：<%=topthread %>
                </strong> <strong>守护线程数: <%=deamonthread %>
                </strong> <br/><br/> <strong style="color: #3300CC">NEW 线程: <%=newcount  %>
                </strong> <strong style="color: #009900">RUNNABLE 线程: <%=runcount %>
                </strong> <strong style="color: #FF0000">BLOCKED 线程: <%=blockedcount %>
                </strong> <strong style="color: #FFCC00">WATTING 线程: <%=waitingcount %>
                </strong> <strong style="color:#CC9933 ">TIMED_WATTING 线程: <%=twaitcount %>
                </strong> <strong style="color: #999999">TERMINATED 线程: <%=terminatedcount %>
                </strong> <br/></p><br/>
                <table style="width: 100%;margin: 0;table-layout:fixed;word-wrap:break-word;word-break:break-all;">
                    <thead>
                    <tr style="font-size: x-small;text-align: left;height: 28">
                        <th width="25%"> Thread Name</th>
                        <th> Thread Info</th>
                    </tr>
                    </thead>
                    <tbody><% String color = "";
                        for (int i = 0; i < threads.size(); i++) {
                            ThreadInfo threadInfo = threads.get(i);
                            Thread.State state = threadInfo.getThreadState();
                            if (state.equals(Thread.State.NEW)) {
                                color = "#3300CC";//蓝色
                            } else if (state.equals(Thread.State.RUNNABLE)) {
                                color = "#009900";//绿色
                            } else if (state.equals(Thread.State.BLOCKED)) {
                                color = "#FF0000";//红色
                            } else if (state.equals(Thread.State.WAITING)) {
                                color = "#FFCC00";//黄色
                            } else if (state.equals(Thread.State.TIMED_WAITING)) {
                                color = "#CC9933";//土色
                            } else if (state.equals(Thread.State.TERMINATED)) {
                                color = "#999999";//灰色
                            }
                            String threadName = threadInfo.getThreadName().length() < 32 ? (threadInfo.getThreadName()) : (threadInfo.getThreadName().substring(0, 32) + "....");
                            ;
                            StackTraceElement[] elements = threadInfo.getStackTrace(); %>
                    <tr>
                        <td class="over" id="<%=threadInfo.getThreadId() %>"
                            style="cursor: pointer;font-size: xx-small;color: <%=color%>"><strong><%=threadName %>
                        </strong></td>
                        <%if (i == 0) {%>
                        <td rowspan="<%=threads.size()%>" valign="top"><p id="stacktrace"
                                                                          style="margin: 10px;font-size: x-small;font-weight: bold;">
                            请求节点:  <%=node[node.length - 1] %><br/><br/> 线程名称:  <%= threadInfo.getThreadName()%>
                            <br/><br/> 线程状态:  <%=threadInfo.getThreadState() %>
                            CPU时间: <%=threadMXBean.getThreadCpuTime(threadInfo.getThreadId()) / (1000 * 1000) %> ms
                            阻塞总数:  <%= threadInfo.getBlockedCount()%>
                            等待总数:  <%= threadInfo.getWaitedCount()%><br/><br/> 堆栈跟踪:
                            <br/><br/> <% for (StackTraceElement e : threadInfo.getStackTrace()) {%>                                                 <%=e.toString() + "<br/>" %>                                            <%} %>
                        </p></td>
                        <% }%></tr>
                    <% } %></tbody>
                </table>
            </div>
            <br/></div>
    </div>
</div>
</body></html>