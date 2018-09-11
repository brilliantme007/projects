from django.shortcuts import render, render_to_response


def sayNo(request):
    return render_to_response("html/index.html")
