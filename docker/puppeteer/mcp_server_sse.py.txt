# MCP = modle control protocol, invented by Anthropic
# This is a demo of how to use MCP with a custom tool
# and run it with FastMCP, a fast implementation of MCP.
# The tool will return the host information in JSON format.
# The MCP server will run and listen for requests, responding with the host information.
# The server can be run with different transports, such as stdio or SSE.
from mcp.server.fastmcp import FastMCP
import tools

# Create a FastMCP instance with a name
mcp = FastMCP("host info mcp", host="0.0.0.0", port=8000)
# Add the custom tool to the MCP instance
mcp.add_tool(tools.webpage_capture)
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
    mcp.run("sse")  # Run the MCP server with SSE transport
    # mcp.run("streamable-http")

if __name__ == "__main__":
    main()

