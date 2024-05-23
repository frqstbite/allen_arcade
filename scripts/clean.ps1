if (Test-Path "..\bin") {
    Remove-Item "..\bin" -Recurse -Force -Confirm:$false
}

if (Test-Path "..\sources.txt") {
    Remove-Item "..\sources.txt" -Force
}