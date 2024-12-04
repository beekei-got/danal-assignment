<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Batch Start</title>
</head>
<body>
    <h3>Batch Result</h3>
    <p>jobId : ${jobId}</p>
    <p>jobName : ${jobName}</p>
    <p>jobStartTime : ${jobStartTime}</p>
    <p>jobEndTime : ${jobEndTime}</p>
    <p>jobStatus : ${jobStatus}</p>
    <button onclick="location.href='/batch/start'">Retry</button>
</body>
</html>
