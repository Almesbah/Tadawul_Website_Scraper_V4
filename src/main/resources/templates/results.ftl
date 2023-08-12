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

        /* Style for the company-basic-info table */
        #company-basic-info {
            border-collapse: collapse; /* collapse borders */
        }

        #company-basic-info th {
            background-color: #6FA8DC; /* header row color */
            border: 1px solid black; /* borders between cells */
        }

       #company-basic-info .special-row {
             background-color: #6FA8DC;
        }

        .alternative tr:nth-child(even) {
             background-color: #CFE2F3;
        }

        .green-cell {
            background-color: green;
        }

        .red-cell {
            background-color: red;
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
    <!-- Assign the id "company-basic-info" to the table -->
    <table id="company-basic-info", class="alternative">
        <tr>
            <!-- Generate headers (company names) -->
            <th>(Indexes) المؤشرات</th>
            <#list results as result>
                <th>${result.companyData.companyName!"No data"}</th>
            </#list>
        </tr>
        <tr>
            <td>(Symbol) رمز الشركة</td>
            <!-- Generate cells (company symbols) -->
            <#list results as result>
               <td>${result.symbol!"No data"}</td>
            </#list>
        </tr>
        <tr>
            <td>(Stock Price) سعر السهم</td>
            <!-- Generate cells (company stock prices) -->
            <#list results as result>
                <td
                <#if result.companyData.stockPrice?number < 30>
                    class="red-cell"
                <#elseif 42 < result.companyData.stockPrice?number>
                    class="green-cell"

                </#if>
                >
                    ${result.companyData.stockPrice!"No data"}
                </td>
            </#list>
        </tr>

        <tr>
            <td>(Issued Shares) عدد الاسهم المصدرة</td>
            <!-- Generate cells (company issued shares) -->
            <#list results as result>
                <td>${result.companyData.companyIssuedShares!"No data"}</td>
            </#list>
        </tr>
        <tr>
            <td>(Paid Capital) رأس المال</td>
            <!-- Generate cells (company paid capital) -->
            <#list results as result>
                <td>${result.companyData.paidCapital!"No data"}</td>
            </#list>
        </tr>
        <tr class="special-row">
              <td>نسب المقارنة للسوق</td>
              <#list results as result>
                <td>----------</td>
              </#list>
        </tr>
        <tr>
            <!-- here we added the term title to give this cell a note for more info -->
            <td title="السعر السوقي للسهم ÷ ربحية السهم
إذا كان مكرر ربحية الشركة 15 فهذا يعني بأن السعر السوقي لهذه الشركة
يعادل ربحية السهم بعدد 15 مره.
وكلما كان مكرر الربحية أقل كان ذلك جيدا للاستثمار
">(PE) مكرر الربحية </td>
            <#list results as result>
                <td>${result.companyIndexes.PE!"No data"}</td>
            </#list>
        </tr>
        <tr>
            <td title="(عدد الاسهم) ÷ (إجمالي الأصول - إجمالي الالتزامات)
.يمكن تسميتها حقوق المساهمين
هي القيمة التي سيحصل عليها المساهمون نظرياً ، في حال تصفية الشركة.
يعتبر السهم مقيما بأقل من قيمته الحقيقة في حال
كانت قيمة السهم الدفترية اعلى من قيمته السوقية
، ما يشجع المستثمرين على الشراء
               ">(BVPS) القيمة الدفترية للسهم</td>
            <#list results as result>
                <td>${result.companyIndexes.BVPS!"No data"}</td>
            </#list>
        </tr>
        <tr>
            <td>(priceToBVPS) مكرر القيمة الدفترية</td>
            <#list results as result>
                <td>${result.companyIndexes.priceToBVPS!"No data"}</td>
            </#list>
        </tr>
         <tr class="special-row">
                  <td>نسب المقارنة للربحية</td>
                    <#list results as result>
                      <td>----------</td>
                    </#list>
         </tr>
        <tr>
            <td>netProfitMargin</td>
            <#list results as result>
                <td>${result.companyIndexes.netProfitMargin!"No data"}</td>
            </#list>
        </tr>
        <tr>
            <td>grossProfitMargin</td>
            <#list results as result>
                <td>${result.companyIndexes.grossProfitMargin!"No data"}</td>
            </#list>
        </tr>
        <tr>
            <td>ROE</td>
            <#list results as result>
            <td>${result.companyIndexes.ROE!"No data"}</td>
            </#list>
        </tr>
        <tr>
            <td>ROA</td>
            <#list results as result>
                <td>${result.companyIndexes.ROA!"No data"}</td>
            </#list>
        </tr>
       <tr>
         <td>ROC</td>
            <#list results as result>
                <td>${result.companyIndexes.ROC!"No data"}</td>
            </#list>
       </tr>
       <tr>
         <td>EPS</td>
            <#list results as result>
                <td>${result.companyIndexes.EPS!"No data"}</td>
            </#list>
       </tr>
        <tr class="special-row">
                    <td>مؤشرات الديون</td>
                    <#list results as result>
                        <td>----------</td>
                    </#list>
        </tr>
        <tr>
         <td>financialLeverage</td>
            <#list results as result>
                <td>${result.companyIndexes.financialLeverage!"No data"}</td>
            </#list>
        </tr>
        <tr>
         <td>debtRatio</td>
            <#list results as result>
                <td>${result.companyIndexes.debtRatio!"No data"}</td>
            </#list>
        </tr>
        <tr>
         <td>dToERatio</td>
            <#list results as result>
                <td>${result.companyIndexes.dToERatio!"No data"}</td>
            </#list>
        </tr>
        <tr class="special-row">
                            <td>مؤشرات السيولة</td>
                            <#list results as result>
                                <td>----------</td>
                            </#list>
        </tr>

       <tr>
         <td>quickLiquidityRatio</td>
            <#list results as result>
                <td>${result.companyIndexes.quickLiquidityRatio!"No data"}</td>
            </#list>
       </tr>

       <tr>
        <td>liquidityRatio</td>
            <#list results as result>
                <td>${result.companyIndexes.liquidityRatio!"No data"}</td>
            </#list>
       </tr>
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
