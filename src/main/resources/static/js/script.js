
function searchItems() {
    var input, filter, currentTitleValue;
    input = document.getElementById("search-term");
    filter = input.value.toUpperCase();
    titles = document.getElementsByTagName("h3");

    for (i = 0; i < titles.length; i++) {
        currentTitleValue = titles[i].textContent || titles[i].innerText;
        if (currentTitleValue.toUpperCase().indexOf(filter) > -1) {
            titles[i].parentElement.parentElement.parentElement.parentElement.style.display = "";
        }
        else {
            titles[i].parentElement.parentElement.parentElement.parentElement.style.display = "none";
        }
    }
}