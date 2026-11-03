{
  inputs = {
    nixpkgs.url = "github:nixos/nixpkgs/nixpkgs-unstable";
    flake-parts.url = "github:hercules-ci/flake-parts";
  };

  outputs =
    inputs@{ ... }:
    inputs.flake-parts.lib.mkFlake { inherit inputs; } {
      systems = [
        "x86_64-linux"
      ];

      perSystem =
        { pkgs, lib, ... }:
        let
          jdks = [
            pkgs.jdk21
          ];

          nativeBuildInputs = jdks ++ [ pkgs.gradle ];
          buildInputs = [ ];
        in
        {

          devShells.default = pkgs.mkShell {

            inherit nativeBuildInputs buildInputs;

            env = {
              LD_LIBRARY_PATH = lib.makeLibraryPath buildInputs;
              JAVA_HOME = "${pkgs.jdk21.home}";
              GRADLE_OPTS = "-Dorg.gradle.java.installations.paths=${pkgs.jdk21} -Dorg.gradle.java.installations.auto-detect=true";
            };
          };
        };
    };
}
