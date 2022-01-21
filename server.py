# server.py
import http.server # Our http server handler for http requests
import socketserver # Establish the TCP Socket connections
 

if '*' in self.cors_origins:
    request.setHeader('Access-Control-Allow-Origin', '*')
    request.setHeader('Access-Control-Allow-Headers', 'Content-Type')  # ADDED
    request.setHeader('Access-Control-Allow-Methods', 'POST, GET, OPTIONS, PUT, DELETE')  # ADDED
elif origin in self.cors_origins:
    request.setHeader('Access-Control-Allow-Origin', origin)
    request.setHeader('Access-Control-Allow-Headers', 'Content-Type')  # ADDED
    request.setHeader('Access-Control-Allow-Methods', 'POST, GET, OPTIONS, PUT, DELETE')  # ADDED

PORT = 8000
 
class MyHttpRequestHandler(http.server.SimpleHTTPRequestHandler):
    def do_GET(self):
        if self.path=='/':
            self.path = 'index.html'
        return http.server.SimpleHTTPRequestHandler.do_GET(self)
 
Handler = MyHttpRequestHandler
 
with socketserver.TCPServer(("", PORT), Handler) as httpd:
    print("Http Server Serving at port", PORT)
    httpd.serve_forever()