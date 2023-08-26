
function sendToServerSingleData(Value, url, csrfToken, SuccMsg, FailMsg) {
  if(SuccMsg == undefined) SuccMsg = "Success"
  if(FailMsg == undefined) FailMsg = "failed"

  var xhr = new XMLHttpRequest();
  xhr.open("POST", url, true);
  xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded", "charset=UTF-8");
  xhr.setRequestHeader("X-CSRF-TOKEN", csrfToken);
  xhr.onreadystatechange = function() {
    if(xhr.readyState === XMLHttpRequest.DONE) {
      if(xhr.status === 200) alert(SuccMsg)
      else alert(FailMsg)
    }
  }
  var formData = "data=" + encodeURIComponent(Value)
  xhr.send(formData);
}


//val1, val2 는 Password로 받는다.
//btn은 버튼으로 받으며 그 뒤의 파라미터는 알아서 맞게 조절.
//ver_0.1
function signUpFormValid(pass, pass2, btn){
    pass2.addEventListener("input", checkPasswordMatch);

    function checkPasswordMatch() {
      if (pass.value == pass2.value) {

        btn.disabled = false;
      }else btn.disabled = true;
    }
}
