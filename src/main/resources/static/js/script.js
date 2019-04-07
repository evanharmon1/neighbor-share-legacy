
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

function confirmDelete() {
    var deleteButton = document.getElementById("delete-button");
    var confirmDelete = document.getElementById("confirm-delete");

    deleteButton.style.display = "none";
    confirmDelete.style.display = "";
}

function confirmImageDelete() {
    var deleteButton = document.getElementById("delete-image-button");
    var confirmDelete = document.getElementById("confirm-delete-image");

    deleteButton.style.display = "none";
    confirmDelete.style.display = "";
}