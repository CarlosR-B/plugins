package dev.quiescence.plugins.versioning

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class VersionTest {

    @Test
    fun `major and minor version`() {
        val version = Version(1, 1)

        assertThat(version.value()).isEqualTo("1.1")
    }

    @Test
    fun `major, minor and patch version`() {
        val version = Version(1, 1, 0)

        assertThat(version.value()).isEqualTo("1.1.0")
    }

    @Test
    fun `major, minor, patch and label version`() {
        val version = Version(1, 1, 0, "RELEASE")

        assertThat(version.value()).isEqualTo("1.1.0-RELEASE")
    }

    @Test
    fun `increment patch version`() {
        val version = Version(1, 1, 0, "RELEASE").incrementPatch()

        assertThat(version.value()).isEqualTo("1.1.1-RELEASE")
    }

    @Test
    fun `increment minor version`() {
        val version = Version(1, 1, 1, "RELEASE").incrementMinor()

        assertThat(version.value()).isEqualTo("1.2.0-RELEASE")
    }

    @Test
    fun `increment minor version without patch`() {
        val version = Version(1, 1).incrementMinor()

        assertThat(version.value()).isEqualTo("1.2")
    }

    @Test
    fun `increment minor version without patch with a label`() {
        val version = Version(1, 1, label = "RELEASE").incrementMinor()

        assertThat(version.value()).isEqualTo("1.2-RELEASE")
    }

    @Test
    fun `increment major version`() {
        val version = Version(1, 1, 1, "RELEASE").incrementMajor()

        assertThat(version.value()).isEqualTo("2.0.0-RELEASE")
    }

    @Test
    fun `increment major version without patch`() {
        val version = Version(1, 1).incrementMajor()

        assertThat(version.value()).isEqualTo("2.0")
    }

    @Test
    fun `parse string to Version`() {
        val versionString = "1.1"

        assertThat(versionString.toVersion()).isEqualTo(Version(1, 1))
    }

    @Test
    fun `parse patch and label string to Version`() {
        val versionString = "1.1.3-RELEASE"

        assertThat(versionString.toVersion()).isEqualTo(Version(1, 1, 3, "RELEASE"))
    }
}