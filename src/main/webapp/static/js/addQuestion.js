$(document).ready(function() {
  $('.navbar-btn').on('show.bs.tab', function(event) {
    $('#questionType').val(event.target.hash.substr(1));
  });
});

let
value = 2;

function addRadio(divName) {
  let
  newdiv = document.createElement('div');
  newdiv.className = "input-group rd";
  newdiv.innerHTML = "<span class='input-group-addon'><input type='radio' name='sc' value='"
      + value
      + "'></span><input name='rawAnswerTextsSc' type='text' class='form-control'>";
  document.querySelector(divName).appendChild(newdiv);
  value++;
};
function removeRadio(divName) {
  $("div.rd").last().remove();
};

function addCheckbox(divName) {
  let
  newdiv = document.createElement('div');
  newdiv.className = "input-group cb";
  newdiv.innerHTML = "<span class='input-group-addon'><input type='checkbox' name='mc' value='"
      + value
      + "'></span><input name='rawAnswerTextsMc' type='text' class='form-control'>";
  document.querySelector(divName).appendChild(newdiv);
  value++;
};
function removeCheckbox(divName) {
  $("div.cb").last().remove();
};
