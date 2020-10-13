<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="com.lld.model.*"%>
<%
  LldVO lldVO = (LldVO) request.getAttribute("lldVO"); //EmpServlet.java (Concroller) �s�Jreq��lldVO���� (�]�A�������X��lldVO, �]�]�A��J��ƿ��~�ɪ�lldVO����)
  %>
<%String[] cities = {"�x�_��", "�s�_��", "�򶩥�", "��饫", "�s�˿�", "�s�˥�", "�]�߿�", "�x����", "�n�뿤", "���ƿ�", "���L��", "�Ÿq��", "�Ÿq��", "�x�n��", "������", "�̪F��", "�y����", "�Ὤ��", "�x�F��", "���", "������", "�s����"};%>
<%String[] accStatuses = {"���ҥ�", "�w�ҥ�", "�b������"};%>
<html>

<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <title>�ЪF��ƭק� - update_lld_input.jsp</title>
    <script src="http://code.jquery.com/jquery-1.12.4.min.js"></script>
    <style>
        table#table-1 {
       background-color: #CCCCFF;
       border: 2px solid black;
       text-align: center;
     }
     table#table-1 h4 {
      color: red;
      display: block;
      margin-bottom: 1px;
    }
    h4 {
      color: blue;
      display: inline;
    }
  </style>
    <style>
        table {
     width: 450px;
     background-color: white;
     margin-top: 1px;
     margin-bottom: 1px;
   }
   table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
</style>
</head>

<body bgcolor='white'>
    ${lldVO.lld_dist!=null}
    <table id="table-1">
        <tr>
            <td>
                <h3>�ЪF��ƭק� - update_lld_input.jsp</h3>
                <h4><a href="<%=request.getContextPath()%>/back-end/lld/select_page.jsp"><img src="images/back1.gif" width="100" height="32" border="0">�^����</a></h4>
            </td>
        </tr>
    </table>
    <h3>��ƭק�:</h3>
    <%-- ���~��C --%>
    <c:if test="${not empty errorMsgs}">
        <font style="color:red">�Эץ��H�U���~:</font>
        <ul>
            <c:forEach var="message" items="${errorMsgs}">
                <li style="color:red">${message}</li>
            </c:forEach>
        </ul>
    </c:if>
    <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/lld/LldServlet" name="update_lld_input" enctype="multipart/form-data">
        <table>
            <tr>
                <td>�ЪF�s��:<font color=red><b>*</b></font>
                </td>
                <td>
                    <%=lldVO.getLld_no()%>
                </td>
            </tr>
            <tr>
                <td>�H�c:</td>
                <td><input type="text" name="lld_email" value="<%=lldVO.getLld_email()%>" readonly /></td> <!-- readonly -->
            </tr>
            <tr>
                <td>�b��:</td>
                <td><input type="text" name="lld_acc" value="<%=lldVO.getLld_acc()%>" readonly /></td> <!-- readonly -->
            </tr>
            <tr>
                <td>�K�X:</td>
                <td><input type="password" name="lld_pwd" value="<%=lldVO.getLld_pwd()%>" readonly /></td> <!-- readonly -->
            </tr>
            <tr>
                <td>������:</td>
                <td><input type="text" name="lld_id" size="10" value="<%=lldVO.getLld_id()%>" /></td>
            </tr>
            <tr>
                <td>�m�W:</td>
                <td><input type="text" name="lld_name" value="<%=lldVO.getLld_name()%>"></td>
            </tr>
            <tr>
                <td>�ͤ�:</td>
                <td><input type="text" name="lld_birth" id="f_date1" /></td>
            </tr>
            <td>�ʧO:</td>
            <td>
                <input type="radio" name="lld_sex" required value=true ${(lldVO.lld_sex)?'checked':''}>�k
                <input type="radio" name="lld_sex" required value=false ${!(lldVO.lld_sex)?'checked':''}>�k
            </td>
            </tr>
            <tr>
                <td>���:</td>
                <td><input type="text" name="lld_mobile" value="<%=lldVO.getLld_mobile()%>" /></td>
            </tr>
            <tr>
                <td>����:</td>
                <td>
                    <select name="lld_city" id="lld_city">
                        <!--�p�G��l��Ʀ�lldVO.lld_city, �W�[�Ĥ@�ӿﶵ"��ܿ���", ��value�ǵ�js�P�_��l��ƬO���ӭ�l���, -->
                        <!--�]��selected, �P�_���B�s�W���ﶵ, �A��"��ܿ���"��value�ȧ令0 -->
                        <!--�p�G�S����l���, ���s�W"��ܿ���", �bjs"��ܿ���"��Ҧ������@�_�s�W-->
                        <c:if test="${lldVO.lld_city!=null}">
                            <option value="${lldVO.lld_city}" id="city_default">��ܿ���
                        </c:if>
                    </select>
                </td>
            </tr>
            <tr>
                <td>�ϰ�:</td>
                <td>
                    <select name="lld_dist" id="lld_dist">
                        <c:if test="${lldVO.lld_dist!=null}">
                            <option value="${lldVO.lld_dist}" id="dist_default">��ܰϰ�
                        </c:if>
                    </select>
                </td>
            </tr>
            <tr>
                <td>�a�}:</td>
                <td><input type="text" name="lld_add" value="<%=lldVO.getLld_add()%>" /></td>
            </tr>
            <tr>
                <td>�b�����A:</td>
                <td>
                    <select name="lld_status">
                        <c:forEach var="accStatus" items="<%=accStatuses%>" varStatus="varStatusName">
                            <option value="${lldVO.lld_status}" ${(lldVO.lld_status==varStatusName.index)?'selected':'' }>${accStatus}
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>�[�J�ɶ�:</td>
                <td>
                    <fmt:formatDate value="${lldVO.lld_jointime}" pattern="yyyy-mm-dd" />
                </td>
            </tr>
            <tr>
                <td><label for="myfiles">�j�Y�K:</label></td>
                <td>
                    <div id="container_pic">
                        <div id="upload"><input id="myfiles" type="file" name="lld_pic" accept="image/*" multiple="multiple">
                            <button id="remove">�R��</button>
                        </div>
                    </div>
                </td>
            </tr>
        </table>
        <br>
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="lld_no" value="${lldVO.lld_no}">
        <input type="submit" value="�e�X�ק�">
    </FORM>
    <!-- =========================================�H�U�� datetimepicker �������]�w========================================== -->
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" />
    <script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
    <script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>
    <style>
        .xdsoft_datetimepicker .xdsoft_datepicker {
            width: 300px;
            /* width:  300px; */
        }
        .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
            height: 151px;
            /* height:  151px; */
        }
    </style>
    <script>
        var area_data = {
            '�x�_��': [
                '������', '�j�P��', '���s��', '�U�ذ�', '�H�q��', '�Q�s��', '�j�w��', '�n���', '�_���', '�����', '�h�L��', '��s��'
            ],
            '�s�_��': [
                '�O����', '�s����', '���s��', '�L�f��', '�H����', '���s��', '�K����', '�U����', '�۪���', '�T�۰�', '��ڰ�', '�����', '���˰�', '�^�d��', '���˰�', '�`�|��', '�����', '�s����', '�W�L��', '�Q�Ӱ�', '���M��', '�éM��', '�g����', '�T�l��', '��L��', '�a�q��', '�T����', 'Ī�w��', '���Ѱ�'
            ],
            '�򶩥�': [
                '���R��', '������', '�H�q��', '���s��', '�w�ְ�', '�x�x��', '�C����'
            ],
            '��饫': [
                '����', '���c��', '�����', '�K�w��', '������', 'Ī�˰�', '�t�s��', '�s���', '�j�˰�', '�j���', '�[����', '�s�ΰ�', '�_����'
            ],
            '�s�˿�': [
                '�˥_��', '�˪F��', '�s�H��', '������', '�o�ܶm', '�_�s�m', '�_�H�m', '��s�m', '�|�L�m', '��f�m', '�s�׶m', '�y�۶m', '���p�m'
            ],
            '�s�˥�': [
                '�F��', '�_��', '���s��'
            ],
            '�]�߿�': [
                '�]�ߥ�', '�q�]��', '�b����', '�˫n��', '�Y����', '���s��', '������', '���m', '�Y�ζm', '���]�m', '���r�m', '�T�q�m', '�y���m', '�T�W�m', '�n�ܶm', '�j��m', '���m', '���w�m'
            ],
            '�O����': [
                '����', '�F��', '�n��', '���', '�_��', '�_�ٰ�', '��ٰ�', '�n�ٰ�', '�ӥ���', '�j����', '���p��', '�Q���', '�׭��', '�Z����', '�F�հ�', '�۩���', '�s����', '�M����', '������', '��l��', '�j����', '�j�{��', '�s����', '�F����', '��ϰ�', '�M����', '�j�Ұ�', '�~�H��', '�j�w��'
            ],
            '�n�뿤': [
                '�n�륫', '�H����', '�����', '�ˤs��', '������', '�W���m', '�����m', '���d�m', '�����m', '��m�m', '�����m', '�H�q�m', '���R�m'
            ],
            '���ƿ�': [
                '���ƥ�', '���L��', '�M����', '������', '�˴���', '�G�L��', '�Ф���', '�_����', '��¶m', '���m', '�j���m', '�ùt�m', '����m', '�u��m', '�ֿ��m', '�q���m', '�H�߶m', '�H�Q�m', '�j���m', '�ڭb�m', '�˶�m', '���Y�m', '�G���m', '�Ч��m', '���Y�m', '�˦{�m'
            ],
            '���L��': [
                '�椻��', '��n��', '�����', '������', '�g�w��', '�_����', '�l��m', '�L���m', '�j�|�m', '�j��m', '�[�I�m', '�G�[�m', '���d�m', '�O��m', '�F�նm', '�ǩ��m', '�|��m', '�f��m', '���L�m', '�����m'
            ],
            '�Ÿq��': [
                '�ӫO��', '���l��', '���U��', '�j�L��', '�����m', '�ˤf�m', '�s��m', '���}�m', '�F�۶m', '�q�˶m', '����m', '���W�m', '���H�m', '�˱T�m', '���s�m', '�f���m', '�j�H�m', '�����s�m'
            ],
            '�Ÿq��': [
                '�F��', '���'
            ],
            '�O�n��': [
                '�����', '�F��', '�n��', '�_��', '�w����', '�w�n��', '�ñd��', '�k����', '�s�ư�', '�����', '�ɤ���', '�����', '�n�ư�', '���w��', '���q��', '�s�T��', '�x�а�', '�¨���', '�Ψ���', '����', '�C�Ѱ�', '�N�x��', '�ǥҰ�', '�_����', '�s���', '�����', '�ժe��', '�F�s��', '���Ұ�', '�U���', '�h���', '�Q����', '���ư�', '�j����', '�s�W��', '�s����', '�w�w��'
            ],
            '������': [
                '�����', '�����', '���s��', '�T����', '�Q�L��', '�e����', '�s����', '�d����', '�e���', '�p���', '�X�z��', '��s��', '�j�d��', '���Q��', '�L���', '���Z��', '�j���', '�j����', '���s��', '���˰�', '���Y��', '��x��', '������', '�æw��', '�P�_��', '�мd��', '������', '�X�_��', '�򤺰�', '�X�s��', '���@��', '������', '���L��', '�ҥP��', '���t��', '�Z�L��', '�緽��', '�����L��'
            ],
            '�̪F��': [
                '�̪F��', '��{��', '�F����', '��K��', '�U���m', '���v�m', '�ﬥ�m', '�E�p�m', '����m', '�Q�H�m', '����m', '�U�r�m', '���H�m', '�˥жm', '�s��m', '�D�d�m', '�s��m', '�r���m', '�L��m', '�n�{�m', '�ΥV�m', '�[�y�m', '�����m', '���{�m', '�D�s�m', '���x�m', '���a�m', '���Z�m', '�Ӹq�m', '�K��m', '��l�m', '�d���m', '�T�a���m'
            ],
            '�y����': [
                '�y����', 'ù�F��', 'Ĭ�D��', '�Y����', '�G�˶m', '����m', '���s�m', '�V�s�m', '�����m', '�T�P�m', '�j�P�m', '�n�D�m'
            ],
            '�Ὤ��': [
                '�Ὤ��', '��L��', '�ɨ���', '�s���m', '�N�w�m', '���׶m', '�q�L�m', '���_�m', '���ضm', '���J�m', '�U�a�m', '�I���m', '���˶m'
            ],
            '�O�F��': [
                '�O�F��', '���\��', '���s��', '���ضm', '���ݶm', '���W�m', '�F�e�m', '�����m', '�����m', '���n�m', '���p�m', '�j�Z�m', '�F���m', '��q�m', '�����m', '�ӳ¨��m'
            ],
            '���': [
                '������', '���m', '�ըF�m', '�����m', '��w�m', '�C���m'
            ],
            '������': [
                '������', '������', '���F��', '����m', '�P���m', '�Q���m'
            ],
            '�s����': [
                '�n��m', '�_��m', '�����m', '�F�޶m'
            ]
        }
    </script>
    <script>
        $.datetimepicker.setLocale('zh');
        $('#f_date1').datetimepicker({
            theme: '', //theme: 'dark',
            timepicker: false, //timepicker:true,
            step: 1, //step: 60 (�o�Otimepicker���w�]���j60����)
            format: 'Y-m-d', //format:'Y-m-d H:i:s',
            value: '<%=lldVO.getLld_birth()%>', // value:   new Date(),
        });


        function init() {
            var container_pic = document.getElementById("container_pic");
            var myfiles = document.getElementById("myfiles");
            myfiles.addEventListener('change', function() {
                var files = myfiles.files;
                if (files !== null) {
                    for (let i = 0; i < files.length; i++) {
                        if (files[i].type.indexOf('image') > -1) {
                            var reader = new FileReader();
                            reader.addEventListener('load', function(e) {
                                pre = document.getElementsByClassName("preview");
                                if (typeof(pre[0]) !== "undefined") {
                                    $(".preview").empty();
                                }
                                var div = document.createElement('div');
                                div.setAttribute("class", "preview");
                                innerElement = '<div class="checkbox"><input id="label' + i + '""' +
                                    ' type="checkbox" name="img' + i + '""' +
                                    ' class="check"></div><label for="label' + i + '""' +
                                    '> <img id="img' + i + '""' + ' src="' + e.target.result +
                                    '"></label>';
                                div.innerHTML = innerElement;
                                container_pic.append(div);
                            });
                            reader.readAsDataURL(files[i]); //****** trigger the action to read the file
                        }
                    }
                }
            });
            var remove = document.getElementById("remove");
            remove.addEventListener('click', function(e) {
                var checkbox = document.getElementsByClassName("checkbox");
                for (var i = checkbox.length - 1; i >= 0; i--) {
                    console.log(checkbox[i])
                    if (checkbox[i].children[0].checked)
                        checkbox[i].parentElement.remove();
                }
                e.preventDefault();
            });
        }

        window.onload = init;

        $(document).ready(function() {
            var city_key = Object.keys(area_data);
            //�Y�L��l���, ��Ҧ������ﶵ��W�h, �ϰ���ϰ�
            if ($('#lld_city').val() === null) {
                $("#lld_city").append('<option value="0" id="city_default">��ܿ���');
                city_key.forEach(function(item, index, array) {
                    $("#lld_city").append('<option value="' + item + '">' + item);
                });
                $("#lld_dist").append('<option value="0" id="dist_default">��ܰϰ�');

                // �Y����l�������, ��W�Ҧ������ﶵ, �B�ӿ�����selected
                // �A��W�ӿ����Ҧ��ϰ�ﶵ, �Ӱϰ쬰selected
            } else {
                var index_city;
                city_key.forEach(function(item, index, array) {
                    if (item === $('#lld_city').val()) {
                        var tmp = ' selected';
                        index_city = index;
                    }
                    $("#lld_city").append('<option value="' + item + '"' + tmp + '>' + item);

                });
                $('#city_default').val("0");
                var dist_values_selectedcity = Object.values(area_data)[index_city];
                dist_values_selectedcity.forEach(function(item, index, array) {
                    var tmp = (item === $('#lld_dist').val()) ? ' selected' : '';
                    $("#lld_dist").append('<option value="' + item + '"' + tmp + '>' + item);
                });
                $('#dist_default').val("0");
            }

            //��ܿ���, �ϰ�s��
            $("#lld_city").change(function() {
                city_key.forEach(function(item, index, array) {
                    if (item === $("#lld_city").val()) {
                        $("#dist_default ~ option").remove();
                        var dist_values = Object.values(area_data)[index];
                        dist_values.forEach(function(item, index, array) {
                            $("#lld_dist").append(
                                '<option value="' + item + '">' + item);
                        });
                    }
                });
            });
        });
    </script>
</body>
</html>