package pl.edu.agh.toik.migration;

import pl.edu.agh.toik.communication.MessagingService;

public interface MigrationAlgorithmFactory {
    MigrationAlgorithm create(MessagingService messagingService);
}
