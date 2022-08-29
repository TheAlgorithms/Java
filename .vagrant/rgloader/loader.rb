# This file loads the proper rgloader/loader.rb file that comes packaged
# with Vagrant so that encoded files can properly run with Vagrant.

if ENV["VAGRANT_INSTALLER_EMBEDDED_DIR"]
  require File.expand_path(
    "rgloader/loader", ENV["VAGRANT_INSTALLER_EMBEDDED_DIR"])
else
  raise "Encoded files can't be read outside of the Vagrant installer."
end
