    package com.torque.app.server.model;
    import java.util.List;
    import lombok.Data;
    import lombok.AllArgsConstructor;
    import lombok.NonNull;
    @Data
    @AllArgsConstructor
    public class Bracket {
        @NonNull
        private String name;
        @NonNull
         //round, match
        private List<List<Match>> matches;
    }
