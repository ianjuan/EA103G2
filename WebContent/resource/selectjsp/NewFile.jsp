<%@ page language="java" contentType="text/html; charset=BIG5"
	pageEncoding="BIG5"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="BIG5">
<title>Insert title here</title>
</head>
<div class="col-12 body">
	<!-- ���� -->
	<div class="row justify-content-md-center sharchbox">
		<div class="col-2"></div>
		<div class="col-10">
			<div id="city">
				<input type="text" id="citych" value="��ܫ���"><br>
				<div class="allcity">
					<label id="citylabel"> <input type="radio" name="city"
						class="cityinput" value="�x�_��"> �x�_��
					</label>
				</div>
				<div class="allcity">
					<label id="citylabel"> <input type="radio" name="city"
						class="cityinput" value="�s�_��"> �s�_��
					</label>
				</div>
				<div class="allcity">
					<label id="citylabel"> <input type="radio" name="city"
						class="cityinput" value="�򶩥�"> �򶩥�
					</label>
				</div>
				<div class="allcity">
					<label id="citylabel"> <input type="radio" name="city"
						class="cityinput" value="��饫"> ��饫
					</label>
				</div>
				<div class="allcity">
					<label id="citylabel"> <input type="radio" name="city"
						class="cityinput" value="�s�˿�"> �s�˿�
					</label>
				</div>
				<div class="allcity">
					<label id="citylabel"> <input type="radio" name="city"
						class="cityinput" value="�s�˥�"> �s�˥�
					</label>
				</div>
				<div class="allcity">
					<label id="citylabel"> <input type="radio" name="city"
						class="cityinput" value="�]�߿�"> �]�߿�
					</label>
				</div>
				<div class="allcity">
					<label id="citylabel"> <input type="radio" name="city"
						class="cityinput" value="�O����"> �O����
					</label>
				</div>
				<div class="allcity">
					<label id="citylabel"> <input type="radio" name="city"
						class="cityinput" value="�n�뿤"> �n�뿤
					</label>
				</div>
				<div class="allcity">
					<label id="citylabel"> <input type="radio" name="city"
						class="cityinput" value="���ƿ�"> ���ƿ�
					</label>
				</div>
				<div class="allcity">
					<label id="citylabel"> <input type="radio" name="city"
						class="cityinput" value="���L��"> ���L��
					</label>
				</div>
				<div class="allcity">
					<label id="citylabel"> <input type="radio" name="city"
						class="cityinput" value="�Ÿq��"> �Ÿq��
					</label>
				</div>
				<div class="allcity">
					<label id="citylabel"> <input type="radio" name="city"
						class="cityinput" value="�Ÿq��"> �Ÿq��
					</label>
				</div>
				<div class="allcity">
					<label id="citylabel"> <input type="radio" name="city"
						class="cityinput" value="�O�n��"> �O�n��
					</label>
				</div>
				<div class="allcity">
					<label id="citylabel"> <input type="radio" name="city"
						class="cityinput" value="������"> ������
					</label>
				</div>
				<div class="allcity">
					<label id="citylabel"> <input type="radio" name="city"
						class="cityinput" value="�̪F��"> �̪F��
					</label>
				</div>
				<div class="allcity">
					<label id="citylabel"> <input type="radio" name="city"
						class="cityinput" value="�y����"> �y����
					</label>
				</div>
				<div class="allcity">
					<label id="citylabel"> <input type="radio" name="city"
						class="cityinput" value="�Ὤ��"> �Ὤ��
					</label>
				</div>
				<div class="allcity">
					<label id="citylabel"> <input type="radio" name="city"
						class="cityinput" value="�O�F��"> �O�F��
					</label>
				</div>
			</div>
			<div id="town">
				<input type="text" id="townch" value="��ܶm��"><br>
			</div>
			<div id="searchbar">
				<form class="form-inline mr-auto">
					<input class="form-control mr-sm-2" id="searchbox" type="text"
						placeholder="Search" aria-label="Search">
					<button class="btn btn-unique btn-rounded btn-sm my-0"
						id="btn-search" type="button">Search</button>
				</form>
			</div>
			<!-- ���� -->
		</div>

	</div>
	<div class="row btn-cho">
		<div class="moneyside">
			<div id="money">
				<label for="btn-group">����϶�</label>
				<div class="btn-group" role="group">
					<button type="button" class="btn btn-secondary moneybtn"
						id="btn-left">5000�H�U</button>
					<button type="button" class="btn btn-secondary moneybtn"
						id="btn-left2">5000-10000</button>
					<button type="button" class="btn btn-secondary moneybtn"
						id="btn-left3">10000-15000</button>
					<button type="button" class="btn btn-secondary moneybtn"
						id="btn-left4">15001-20000</button>
					<button type="button" class="btn btn-secondary moneybtn"
						id="btn-left5">20000�H�W</button>
				</div>
			</div>
		</div>
		<div class="houseside">
			<div id="housestyle">
				<label for="btn-group">�ЫΫ��A</label>
				<div class="btn-group" role="group">
					<button type="button" class="btn btn-secondary housebtn">����</button>
					<button type="button" class="btn btn-secondary housebtn">��h��a</button>
					<button type="button" class="btn btn-secondary housebtn">�W�߮M��</button>
					<button type="button" class="btn btn-secondary housebtn">�����M��</button>
					<button type="button" class="btn btn-secondary housebtn">����</button>
				</div>
			</div>
		</div>
	</div>
	<div class="row turn">
		<div class="col-6">
			<div class="col-xs-12 someoption">
				<label class="item_name">�i�}��&nbsp;&nbsp;
					<div class="onoffswitch">
						<input type="checkbox" name="furniture"
							class="onoffswitch-checkbox" id="coke" tabindex="0"> <label
							class="onoffswitch-label" for="coke"></label>
					</div>
				</label> <label class="item_name"><i class="fas fa-paw"></i>

					�i�i�d&nbsp;&nbsp;
					<div class="onoffswitch">
						<input type="checkbox" name="furniture"
							class="onoffswitch-checkbox" id="pet" tabindex="0"> <label
							class="onoffswitch-label" for="pet"></label>
					</div> </label> <label class="item_name">�i�}��&nbsp;&nbsp;
					<div class="onoffswitch">
						<input type="checkbox" name="furniture"
							class="onoffswitch-checkbox" id="deriver" tabindex="0"> <label
							class="onoffswitch-label" for="deriver"></label>
					</div>
				</label> <label class="item_name">�i�s�W&nbsp;&nbsp;
					<div class="onoffswitch">
						<input type="checkbox" name="furniture"
							class="onoffswitch-checkbox" id="try14" tabindex="0"> <label
							class="onoffswitch-label" for="try14"></label>
					</div>
				</label>
			</div>
		</div>
		<div class="col-6">
			<div id="selectforwhat">
				<label class="item_name">�Ƨ�&nbsp;&nbsp; <select
					class="form-control" id="selectbox">
						<option value="date_up">�w�W�[�ɶ� �u->��</option>
						<option value="date_down">�w�W�[�ɶ� ��->�u</option>
						<option value="price_down ">�X������ �C->��</option>
						<option value="price_up">�X������ ��->�C</option>
						<option value="xlhouse">�ЫΩW�� �j->�p</option>
						<option value="xshouse">�ЫΩW�� �p->�j</option>
				</select>
				</label>
			</div>
		</div>
	</div>
	<body>
	<style>
	.someoption{
    width: 70%;
    height: 100%;
    float: right;
        padding-top: 3%;

}
footer{
    bottom: 0;
    background-color: green;
    height: 100px; 
    width: 100%
}
.btn-group button{
    width: 120px;
    height: 38px;
    border-radius:20px;
}
.moneyside,.houseside{
    width: 730px;
    height: 38px;
    
}
.houseside{
margin-left:20px;
}
#money{float: right}
#selectforwhat{
text-align: center;}
.onoffswitch {
            position: relative;
            width: 50px;
            -webkit-user-select: none;
            -moz-user-select: none;
            -ms-user-select: none;
            float: right;
        }

        .onoffswitch-checkbox {
            position: absolute;
            opacity: 0;
            pointer-events: none;
        }

        .onoffswitch-label {
            display: block;
            overflow: hidden;
            cursor: pointer;
            height: 21px;
            width: 50px;
            padding: 0;
            line-height: 21px;
            border: 2px solid #E3E3E3;
            border-radius: 21px;
            background-color: #FFFFFF;
            transition: background-color 0.3s ease-in;
        }

        .onoffswitch-label:before {
            content: "";
            display: block;
            width: 21px;
            margin: 0px;
            background: #FFFFFF;
            position: absolute;
            top: 0;
            bottom: 0;
            right: 27px;
            border: 2px solid #E3E3E3;
            border-radius: 21px;
            transition: all 0.3s ease-in 0s;
        }

        .onoffswitch-checkbox:checked+.onoffswitch-label {
            background-color: #49E845;
        }

        .onoffswitch-checkbox:checked+.onoffswitch-label, .onoffswitch-checkbox:checked+.onoffswitch-label:before {
            border-color: #49E845;
        }

        .onoffswitch-checkbox:checked+.onoffswitch-label:before {
            right: 0px;
        }

        /*�}��*/
             /*����*/
        #searchbar {
            float: left;
            width: 38%;
            margin-left: 3%;
            margin-bottom: 3%;
        }

        .my-0 {
            background-color: pink;
            /*�j�����s*/
        }

        .mr-auto {
            margin-top: 5%;
        }


        #town {
            /*�~�hDIV*/
            height: 20px;
            display: inline;
            margin-top: 2.2%;
            float: left;
            float: left;
            margin-left: 2%;
        }

        .form-inline .form-control {
            width: 70%;
        }

        #townch {
            /*�j����*/
            text-align: center;
            width: 110px;
            height: 40px;
            border-radius: 20px;
            font-size: 20px;
            font-weight: bold;
            letter-spacing: 3px;
            font-family: Microsoft JhengHei;
        }

        #city {
            /*�~�hDIV*/
            height: 20px;
            display: inline;
            margin-left: 10%;
            margin-top: 2.2%;
            float: left;
            border-radius: 50px;
        }

        #citych {
            /*�j����*/
            text-align: center;
            width: 110px;
            height: 40px;
            border-radius: 50px;
            font-weight: bold;
            letter-spacing: 3px;
            font-family: "����-�c", "�L�n������", sans-serif;
            font-size: 20px;
        }

        .allcity {
            /*�����C����*/
            font-weight: bold;
            letter-spacing: 3px;
            font-family: "����-�c", "�L�n������", sans-serif;
            text-align: center;
            font-size: 20px;
            border: 2px #A67F78 ridge;
            width: 150px;
            height: 40px;
            border-radius: 5px;
            z-index: 5;
            position: relative;
            display: none;
            background-color: #E1DCD9;
        }

        .allcity:hover {
            background-color: green;
        }

        .towns {
            /*�m��C����*/
            font-weight: bold;
            letter-spacing: 3px;
            font-family: Microsoft JhengHei;
            text-align: center;
            border: 2px #FFAC55 ridge;
            font-size: 20px;
            width: 150px;
            height: 40px;
            border-radius: 50px;
            z-index: 5;
            position: relative;
            display: none;
            background-color: #FBE1FA;
        }

        .towns:hover {
            background-color: green;
        }

        /*����--------------------------------*/
	</style>
	<script>
$(".form-control").change(function(){
			
	$.ajax({//�s�J��Ʈw���q
		  url:"/EA103G2/mapServlet",
	 	  type:"GET",
	 	  data:{action:"search",
	 		  data:$("#citych").val()+$("#townch").val()+$("#searchbox").val(),
	 		  sort:$("#selectbox").val()
	 	  },
	 	  success:function(data){//�H�W���\�~����
	 		  console.log("data="+data);
//	 		  if(data.length>50){
	 			 obj=JSON.parse(data); 			
	             loading();
	 		  	console.log("res��");}
	 	  ,
	 	  error:function(data)
	 	  {
	 		  console.log("�u������")
	 	  }			  
	  })});
		
		
		$("#city").click(function() {
            $(".allcity").toggle();
        });
        $("#town").click(function() {
            $(".towns").toggle();
        });
        $(".cityinput").click(function() {
            $(".allcity").toggle();
            $("#citych").val($(this).val());
            $(".towns").remove();
            for (let i of studentMap) {
                if (i[0] == $(this).val()) {
                    $("#townch").val("");
                    for (let o of i[1]) { 
                        $("#town").append(
                            "<div class='towns'><label id='townlabel'>" +
                            "<input type='radio'  name='town' class='towninput' value='" + o + "'>" +
                             o + "</label></div>");
                    }
                }
            }
        })
        $(document).on("change", ".towninput", function() {
            $("#townch").val($(this).val());
            $(".towns").toggle();

        });
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
        const studentMap = new Map(Object.entries(area_data));
		
		$(".moneybtn").click(function(){
			$(".moneybtn").css("color","white");
			$(this).css("color","#CD4A2D");
		})
		$(".housebtn").click(function(){
			$(".housebtn").css("color","white");
			$(this).css("color","#CD4A2D");
		})
		</script>
	</body>
</html>