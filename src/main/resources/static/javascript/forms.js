
document.getElementById("landingPageForm").addEventListener("submit", showLoadingText);
let loading = document.getElementById('landingLoadingText')

function showLoadingText() {
    console.log("THE SHOW TEXT FUNCTION WAS CALLED")
  loading.style.display = "block";
}