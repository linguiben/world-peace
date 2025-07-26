# MCP = modle control protocol, invented by Anthropic
# This is a demo of how to use MCP with a custom tool
# and run it with FastMCP, a fast implementation of MCP.
# The tool will return the host information in JSON format.
# The MCP server will run and listen for requests, responding with the host information.
# The server can be run with different transports, such as stdio or SSE.
from starlette.applications import Starlette
from starlette.routing import Mount, Host
import uvicorn
from mcp.server.fastmcp import FastMCP
import tools

# Create a FastMCP instance with a name
mcp = FastMCP("host info mcp", host="0.0.0.0", port=8000, stateless_http=True)
# mcp = FastMCP("host info mcp")
# mcp = FastMCP("host info mcp", host="0.0.0.0", port=8000)
# mcp.settings.mount_path = "/hsil/sse"
# Mount the SSE server to the existing ASGI server
# app = Starlette(
#     routes=[
#         Mount("/hsil/sse", app=mcp.sse_app()),
#         Mount("/hsil/sse/", app=mcp.sse_app()),
#     ]
# )

# Add the custom tool to the MCP instance
mcp.add_tool(tools.webpage_capture)
mcp.add_tool(tools.run_cmd)

@mcp.tool()
def system_info():
    """Get system information and return it as a JSON string."""
    import platform
    import json
    system_info = {
        "system": platform.system(),
        "node": platform.node(),
        "release": platform.release(),
        "version": platform.version(),
        "machine": platform.machine(),
        "processor": platform.processor(),
        "platform": platform.platform(),
    }
    return json.dumps(system_info, indent=2)

# stido transport is useful for local testing, while sse is useful for web applications.
def main():
    # mcp.run("stdio") # Run the MCP server with stdio transport or sse (Server-Sent Events)
    # mcp.run("sse", mount_path="/hsil/sse")
    # mcp.run("streamable-http")
    mcp.run(transport="streamable-http")
    # uvicorn.run("mcp_server_sse:app", host="0.0.0.0", port=8000, reload=True)

if __name__ == "__main__":
    main()

