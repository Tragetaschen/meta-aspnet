DESCRIPTION = ".NET Core Libraries (CoreFX)"
HOMEPAGE = "http://dot.net/"
LICENSE = "MIT"
SECTION = "devel"

DEPENDS = "clang-native coreclr libunwind gettext icu openssl util-linux cmake-native krb5 curl"
RDEPENDS_${PN} = "coreclr libcurl"

SRC_URI = "git://github.com/dotnet/corefx.git;branch=release/${PV};\
    file://toolchain.patch; \
    file://Fix-unable-to-get-runtime-System.Security.Cryptograp.patch; \
"
SRCREV = "release/${PV}"
LIC_FILES_CHKSUM = "file://LICENSE;md5=e3bc66d8592c758a2ec26df8209f71e3"
S = "${WORKDIR}/git"

do_configure() {
	cd ${S}
	./init-tools.sh
}

do_compile() {
	cd ${S}
	ROOTFS_DIR=${STAGING_DIR_HOST} ./build-native.sh -release -buildArch=arm -- verbose cross
	# Bitbake sets bindir ("/usr/bin") which MsBuild would happily pick up
	# as BinDir to store the built libraries in
	unset bindir
	./build-managed.sh -release -skiptests
}

do_install() {
	export src="${S}/bin"
	export target="${D}/opt/dotnet/"

	install -d ${target}

	for i in `cd ${src}/Linux.arm.Release/Native/ && ls *.so`
	do
		# The non-stripped versions are called *.so.dbg [already-stripped]
		install -m 0755 ${src}/Linux.arm.Release/Native/${i}.dbg ${target}/${i}
	done
	
	for i in ${DLLS}
	do
		install -m 0644 ${src}/${i} ${target}
	done
}

FILES_${PN} = "/opt/dotnet"

DLLS = "\
  Linux.AnyCPU.Release/System.Data.SqlClient/System.Data.SqlClient.dll \
  Linux.AnyCPU.Release/System.Diagnostics.Debug/System.Diagnostics.Debug.dll \
  Linux.AnyCPU.Release/System.Diagnostics.Process/System.Diagnostics.Process.dll \
  Linux.AnyCPU.Release/System.IO.FileSystem.Watcher/System.IO.FileSystem.Watcher.dll \
  Unix.AnyCPU.Release/Microsoft.Win32.Primitives/Microsoft.Win32.Primitives.dll \
  Unix.AnyCPU.Release/Microsoft.Win32.Registry/Microsoft.Win32.Registry.dll \
  Unix.AnyCPU.Release/System.Console/System.Console.dll \
  Unix.AnyCPU.Release/System.Diagnostics.FileVersionInfo/System.Diagnostics.FileVersionInfo.dll \
  Unix.AnyCPU.Release/System.Globalization.Extensions/System.Globalization.Extensions.dll \
  Unix.AnyCPU.Release/System.IO.Compression/System.IO.Compression.dll \
  Unix.AnyCPU.Release/System.IO.FileSystem/System.IO.FileSystem.dll \
  Unix.AnyCPU.Release/System.IO.MemoryMappedFiles/System.IO.MemoryMappedFiles.dll \
  Unix.AnyCPU.Release/System.Net.Http/System.Net.Http.dll \
  Unix.AnyCPU.Release/System.Net.NameResolution/System.Net.NameResolution.dll \
  Unix.AnyCPU.Release/System.Net.Primitives/System.Net.Primitives.dll \
  Unix.AnyCPU.Release/System.Net.Requests/System.Net.Requests.dll \
  Unix.AnyCPU.Release/System.Net.Security/System.Net.Security.dll \
  Unix.AnyCPU.Release/System.Net.Sockets/System.Net.Sockets.dll \
  Unix.AnyCPU.Release/System.Private.Uri/System.Private.Uri.dll \
  Unix.AnyCPU.Release/System.Runtime/System.Runtime.dll \
  Unix.AnyCPU.Release/System.Runtime.Extensions/System.Runtime.Extensions.dll \
  Unix.AnyCPU.Release/System.Runtime.InteropServices.RuntimeInformation/System.Runtime.InteropServices.RuntimeInformation.dll \
  Unix.AnyCPU.Release/System.Security.Cryptography.Algorithms/System.Security.Cryptography.Algorithms.dll \
  Unix.AnyCPU.Release/System.Security.Cryptography.Cng/System.Security.Cryptography.Cng.dll \
  Unix.AnyCPU.Release/System.Security.Cryptography.Csp/System.Security.Cryptography.Csp.dll \
  Unix.AnyCPU.Release/System.Security.Cryptography.Encoding/System.Security.Cryptography.Encoding.dll \
  Unix.AnyCPU.Release/System.Security.Cryptography.OpenSsl/System.Security.Cryptography.OpenSsl.dll \
  Unix.AnyCPU.Release/System.Security.Cryptography.X509Certificates/System.Security.Cryptography.X509Certificates.dll \
  Unix.AnyCPU.Release/System.Security.Principal.Windows/System.Security.Principal.Windows.dll \
  Unix.AnyCPU.Release/System.Text.Encoding.CodePages/System.Text.Encoding.CodePages.dll \
  Unix.AnyCPU.Release/System.Threading.Overlapped/System.Threading.Overlapped.dll \
  AnyOS.AnyCPU.Release/System.AppContext/System.AppContext.dll \
  AnyOS.AnyCPU.Release/System.Buffers/System.Buffers.dll \
  AnyOS.AnyCPU.Release/System.Collections/System.Collections.dll \
  AnyOS.AnyCPU.Release/System.Collections.Concurrent/System.Collections.Concurrent.dll \
  AnyOS.AnyCPU.Release/System.Collections.Immutable/System.Collections.Immutable.dll \
  AnyOS.AnyCPU.Release/System.ComponentModel/System.ComponentModel.dll \
  AnyOS.AnyCPU.Release/System.ComponentModel.Annotations/System.ComponentModel.Annotations.dll \
  AnyOS.AnyCPU.Release/System.Data.Common/System.Data.Common.dll \
  AnyOS.AnyCPU.Release/System.Diagnostics.DiagnosticSource/System.Diagnostics.DiagnosticSource.dll \
  AnyOS.AnyCPU.Release/System.Diagnostics.StackTrace/System.Diagnostics.StackTrace.dll \
  AnyOS.AnyCPU.Release/System.Diagnostics.Tools/System.Diagnostics.Tools.dll \
  AnyOS.AnyCPU.Release/System.Diagnostics.Tracing/System.Diagnostics.Tracing.dll \
  AnyOS.AnyCPU.Release/System.Dynamic.Runtime/System.Dynamic.Runtime.dll \
  AnyOS.AnyCPU.Release/System.Globalization/System.Globalization.dll \
  AnyOS.AnyCPU.Release/System.Globalization.Calendars/System.Globalization.Calendars.dll \
  AnyOS.AnyCPU.Release/System.IO/System.IO.dll \
  AnyOS.AnyCPU.Release/System.IO.Compression.ZipFile/System.IO.Compression.ZipFile.dll \
  AnyOS.AnyCPU.Release/System.IO.FileSystem.Primitives/System.IO.FileSystem.Primitives.dll \
  AnyOS.AnyCPU.Release/System.IO.UnmanagedMemoryStream/System.IO.UnmanagedMemoryStream.dll \
  AnyOS.AnyCPU.Release/System.Linq/System.Linq.dll \
  AnyOS.AnyCPU.Release/System.Linq.Expressions/System.Linq.Expressions.dll \
  AnyOS.AnyCPU.Release/System.Linq.Parallel/System.Linq.Parallel.dll \
  AnyOS.AnyCPU.Release/System.Linq.Queryable/System.Linq.Queryable.dll \
  AnyOS.AnyCPU.Release/System.Net.WebHeaderCollection/System.Net.WebHeaderCollection.dll \
  AnyOS.AnyCPU.Release/System.Numerics.Vectors/System.Numerics.Vectors.dll \
  AnyOS.AnyCPU.Release/System.ObjectModel/System.ObjectModel.dll \
  AnyOS.AnyCPU.Release/System.Reflection/System.Reflection.dll \
  AnyOS.AnyCPU.Release/System.Reflection.DispatchProxy/System.Reflection.DispatchProxy.dll \
  AnyOS.AnyCPU.Release/System.Reflection.Emit/System.Reflection.Emit.dll \
  AnyOS.AnyCPU.Release/System.Reflection.Emit.ILGeneration/System.Reflection.Emit.ILGeneration.dll \
  AnyOS.AnyCPU.Release/System.Reflection.Emit.Lightweight/System.Reflection.Emit.Lightweight.dll \
  AnyOS.AnyCPU.Release/System.Reflection.Extensions/System.Reflection.Extensions.dll \
  AnyOS.AnyCPU.Release/System.Reflection.Metadata/System.Reflection.Metadata.dll \
  AnyOS.AnyCPU.Release/System.Reflection.Primitives/System.Reflection.Primitives.dll \
  AnyOS.AnyCPU.Release/System.Reflection.TypeExtensions/System.Reflection.TypeExtensions.dll \
  AnyOS.AnyCPU.Release/System.Resources.Reader/System.Resources.Reader.dll \
  AnyOS.AnyCPU.Release/System.Resources.ResourceManager/System.Resources.ResourceManager.dll \
  AnyOS.AnyCPU.Release/System.Runtime.Handles/System.Runtime.Handles.dll \
  AnyOS.AnyCPU.Release/System.Runtime.InteropServices/System.Runtime.InteropServices.dll \
  AnyOS.AnyCPU.Release/System.Runtime.Loader/System.Runtime.Loader.dll \
  AnyOS.AnyCPU.Release/System.Runtime.Numerics/System.Runtime.Numerics.dll \
  AnyOS.AnyCPU.Release/System.Runtime.Serialization.Primitives/System.Runtime.Serialization.Primitives.dll \
  AnyOS.AnyCPU.Release/System.Security.Claims/System.Security.Claims.dll \
  AnyOS.AnyCPU.Release/System.Security.Cryptography.Primitives/System.Security.Cryptography.Primitives.dll \
  AnyOS.AnyCPU.Release/System.Security.Principal/System.Security.Principal.dll \
  AnyOS.AnyCPU.Release/System.Text.Encoding/System.Text.Encoding.dll \
  AnyOS.AnyCPU.Release/System.Text.Encoding.Extensions/System.Text.Encoding.Extensions.dll \
  AnyOS.AnyCPU.Release/System.Text.RegularExpressions/System.Text.RegularExpressions.dll \
  AnyOS.AnyCPU.Release/System.Threading/System.Threading.dll \
  AnyOS.AnyCPU.Release/System.Threading.Tasks/System.Threading.Tasks.dll \
  AnyOS.AnyCPU.Release/System.Threading.Tasks.Dataflow/System.Threading.Tasks.Dataflow.dll \
  AnyOS.AnyCPU.Release/System.Threading.Tasks.Extensions/System.Threading.Tasks.Extensions.dll \
  AnyOS.AnyCPU.Release/System.Threading.Tasks.Parallel/System.Threading.Tasks.Parallel.dll \
  AnyOS.AnyCPU.Release/System.Threading.Thread/System.Threading.Thread.dll \
  AnyOS.AnyCPU.Release/System.Threading.ThreadPool/System.Threading.ThreadPool.dll \
  AnyOS.AnyCPU.Release/System.Threading.Timer/System.Threading.Timer.dll \
  AnyOS.AnyCPU.Release/System.Xml.ReaderWriter/System.Xml.ReaderWriter.dll \
  AnyOS.AnyCPU.Release/System.Xml.XDocument/System.Xml.XDocument.dll \
  AnyOS.AnyCPU.Release/System.Xml.XPath/System.Xml.XPath.dll \
  AnyOS.AnyCPU.Release/System.Xml.XmlDocument/System.Xml.XmlDocument.dll \
  AnyOS.AnyCPU.Release/System.Xml.XPath.XDocument/System.Xml.XPath.XDocument.dll \
"
