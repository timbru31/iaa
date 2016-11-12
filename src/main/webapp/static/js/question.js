/*
 * JS to add and remove questions (radio or checkboxes) dynamically
 * Author: Yannick Rump
 */

$(document).ready(function() {
  $('.navbar-btn').on('show.bs.tab', function(event) {
    $('#questionType').val(event.target.hash.substr(1));
  });
});

// per default 2 answers are displayed
var radioValue = 2;
var checkboxValue = 2;

function addRadio() {
  var newDiv = document.createElement('div');
  newDiv.className = "input-group rd";
  newDiv.innerHTML = "<span class='input-group-addon'><input type='radio' name='sc' value='" + radioValue + "'></span><input name='rawAnswerTextsSc' type='text' class='form-control'>";
  $("#radios").append(newDiv);
  radioValue++;
}

function removeRadio() {
  $("div.rd").last().remove();
  radioValue--;
};

function addCheckbox() {
  var newDiv = document.createElement('div');
  newDiv.className = "input-group cb";
  newDiv.innerHTML = "<span class='input-group-addon'><input type='checkbox' name='mc' value='" + checkboxValue + "'></span><input name='rawAnswerTextsMc' type='text' class='form-control'>";
  $("#checkboxes").append(newDiv)
  checkboxValue++;
}

function removeCheckbox() {
  $("div.cb").last().remove();
  checkboxValue--;
}
