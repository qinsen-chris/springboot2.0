<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE html
        PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">


<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

    <head>
        <title>Flying Saucer: CSS List Support</title>
        <link rel="stylesheet" type="text/css" href="general.css" title="Style" media="screen" />

        <meta http-equiv='Content-Type' content="text/html; charset=UTF-8" />
        <meta name='Generator' content="Microsoft Word 15 (filtered)" />
        <style>

            
            body {
		        background-color: white;
		        border: 0px;
		        margin: 0;
		        padding: 15;
		        text-align: center;
						/*font-family: Arial Unicode MS;*/
						font-family: SimSun; /*宋体*/
      			}

            @page{size:a4}

        </style>

    </head>

    <body lang='ZH-CN' style='text-justify-trim:punctuation'>

        <div class='WordSection1' style='layout-grid:15.6pt'>

            <p align='right' style='text-align:right'><span lang='EN-US'>&nbsp;</span></p>

            <p class='MsoNormal' align='right' style='text-align:right'><b><span lang='EN-US'
                                                                                 style='font-size:18.0pt'>&nbsp;
                &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            </span></b><span style='text-indent:24.0pt;'>合同编号：</span><span
                    lang='EN-US' style='font-size:10.5pt'>${contractNo1!""}</span><span lang='EN-US'> </span></p>

            <p class='MsoNormal' align='center' style='text-align:center'><b><span
                    style='font-size:18.0pt;'>应收账款转让合同</span></b> </p>

            <p class='MsoNormal'><span lang='EN-US'>&nbsp; </span></p>

            <p class='MsoNormal' style='text-indent:24.0pt'><span >为保障投资人（即本合同乙方）的合法权益，请投资人在选择购买本合同约定的产品及接受本合同约定的服务前仔细阅读本合同所有内容，尤其注意以<b>粗体下划线</b>格式标注的内容。当乙方点击确认本合同时，即表示乙方已充分阅读、理解并同意接受本合同之所有内容。</span>
            <#list animals as animal>
    					<li>${animal.name} for ${animal.price} </li>
  					</#list>
  					
            </p>



        </div>

    </body>

</html>
