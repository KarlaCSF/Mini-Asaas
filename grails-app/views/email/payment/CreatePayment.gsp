<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <title>Email</title>
</head>

<body>
<div align="center" style="width:100%;max-width:872px;margin:0 auto;background-color:#fff;border-radius:10px 10px 0 0">

    <div style="display:block;width:100%;border-top-right-radius:4px;border-top-left-radius:4px;overflow:hidden;letter-spacing:unset;box-sizing:border-box;font-family:'Open Sans',sans-serif!important">
        <div style="display:table;width:100%">
            <div style="background-color:#0030b9;color:#ffffff;display:table-cell;padding:24px;word-break:break-word;text-align:left;width:100%">
                <h1 style="color:#ffffff;margin:0;font-size:24px;font-style:normal;font-weight:600;line-height:28.8px;margin-bottom:8px;font-family:'Open Sans',sans-serif!important;letter-spacing:unset">
                    ${data.customer.name}
                </h1>

                <h2 style="color:#ffffff;margin:0;font-size:20px;font-style:normal;font-weight:600;line-height:24px;font-family:'Open Sans',sans-serif!important;letter-spacing:unset">
                    ${data.customer.cpfCnpj}
                </h2>
            </div>
        </div>
    </div>

    <div style="border-top:1px solid #cdcdcd;width:100%"></div>

    <table style="background-color:#fff;color:#212529;padding:16px 40px;font-size:20px;line-height:24px;font-weight:600;margin:0 auto;width:100%;max-width:872px;border-collapse:separate">
        <tbody>
        <tr>
            <td>
                <p style="margin:0;text-align:center">
                    Olá, uma cobrança foi gerada para você
                </p>
            </td>
        </tr>
        <tr>
            <td>
                <div style="border-bottom:1px solid #cdcdcd;height:16px;width:100%"></div>
            </td>
        </tr>
        </tbody>
    </table>


    <div style="max-width:792px;padding:0 40px;color:#212529;font-size:14px;line-height:20px;margin:0 auto;text-align:left">
        <p style="font-weight:600;margin:0 0 16px">
            Olá, ${data.payer.name}
        </p>

        <p style="margin:0 0 16px">
            ${data.customer.name} gerou uma cobrança para você, no valor de

            <span style="font-weight:600">
                <g:formatNumber number="${data.value}" type="currency" currencyCode="BRL" />
            </span>
            com vencimento em
            <span style="font-weight:600">
                <g:formatDate date="${data.dueDate}" format="dd MMMM yyyy"/>
            </span>.
        </p>

        <p style="margin:0 0 16px">
            <span style="font-weight:600">
                Descrição da cobrança:
            </span>
            Descrição não informada
        </p>

        <p style="margin:0 0 16px;margin:0">
            Clique no botão abaixo para visualizar a cobrança.
            <a style="color:#0d6efd"
               href="http://localhost:8080/payment/show/${data.id}"
               target="_blank"
               data-saferedirecturl="http://localhost:8080/payment/show/${data.id}">
                <wbr>
                Ou acesse aqui
            </a>
        </p>
    </div>

    <table style="width:100%;max-width:872px;padding:26px 40px;line-height:20px;margin:0 auto;text-align:left">
        <tbody>
        <tr>
            <td width="25" align="center">
                <div style="text-align:center">
                    <a style="font-size:16px;font-weight:700;text-decoration:none;color:#fff;background-color:#0030b9;padding:12px 32px;border-radius:40px;background-color:#198754;display:flex;max-width:max-content;margin:auto;text-align:center"
                       href="http://localhost:8080/payment/show/${data.id}"
                       target="_blank"
                       data-saferedirecturl="http://localhost:8080/payment/show/${data.id}">
                        Visualizar cobrança
                    </a>
                </div>
            </td>
        </tr>
        </tbody>
    </table>


    <table style="width:100%;max-width:872px;margin:0;padding:16px 40px;text-align:center;background-color:#f8f9fa;line-height:20px;color:#212529">
        <tbody>
        <tr>
            <td>

                <p style="text-align:center;font-size:14px;margin:0 auto;font-weight:600">
                    ${data.customer.name}
                </p>


                <p style="text-align:center;font-size:14px;margin:0 auto">
                    <a style="text-decoration:none;font-size:14px;color:rgba(0,0,0,0.87)">
                        ${data.customer.email}
                    </a>
                </p>


                <p style="text-align:center;font-size:14px;margin:0 auto">
                    (XX) XXXXX-6945
                </p>


                <p style="text-align:center;font-size:14px;margin:0 auto">
                    ${data.customer.address.state} - ${data.customer.address.city}
                </p>

            </td>
        </tr>
        </tbody>
    </table>

</div>
</body>
</html>
