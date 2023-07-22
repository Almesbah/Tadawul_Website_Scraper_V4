<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Fundamental Analysis Results</title>
</head>
<body>
    <!-- Add this line for debugging -->
    <p>Template loaded</p>

    <h1>Fundamental Analysis Results</h1>

    <form action="/submit" method="post">
        <label for="companySymbols">Enter company symbols (comma-separated):</label>
        <input type="text" id="companySymbols" name="companySymbols" />
        <button type="submit">Submit</button>
    </form>

    <#if results?has_content>
    <table>
        <tr>
    <th>Company Symbol</th>
    <th>Company Name</th>
    <th>Stock Price</th>
    <th>Issued Shares</th>
    <th>Paid Capital</th>
    <!-- Remaining headers... -->
</tr>
        <#list results as result>
        <tr>
    <td>${result.symbol!"No data"}</td>
    <td>${result.companyData.companyName!"No data"}</td>
    <td>${result.companyData.stockPrice!"No data"}</td>
    <td>${result.companyData.companyIssuedShares!"No data"}</td>
    <td>${result.companyData.paidCapital!"No data"}</td>
    <!-- Remaining fields... -->
</tr>

        </#list>
    </table>
    </#if>
</body>
</html>
