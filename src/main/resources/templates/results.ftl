<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Fundamental Analysis Results</title>
    <style>
        /* Hide the spinner by default */
        #loading-spinner {
            display: none;
        }
    </style>
</head>
<body>
    <h1>Fundamental Analysis Results</h1>

    <!-- Give the form an id so that we can listen for its submit event -->
    <form id="myForm" action="/submit" method="post">
        <label for="companySymbols">Enter company symbols (comma-separated):</label>
        <input type="text" id="companySymbols" name="companySymbols" />
        <button type="submit">Submit</button>
    </form>

    <!-- Add a loading spinner. Replace /path/to/spinner.gif with the path to your spinner. -->
    <div id="loading-spinner">
        <img src="/icons/loadingicon.gif" alt="Loading..." />
    </div>

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

    <script>
        // Listen for the form's submit event
        document.getElementById('myForm').addEventListener('submit', function() {
            // Show the loading spinner
            document.getElementById('loading-spinner').style.display = 'block';
        });

        // Listen for the window's load event
        window.onload = function() {
            // Hide the loading spinner
            document.getElementById('loading-spinner').style.display = 'none';
        };
    </script>
</body>
</html>

